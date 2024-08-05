package org.example.controller;

import lombok.extern.log4j.Log4j2;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Controller
public class BibleController {

    @GetMapping("/")
    public String index() {
        return "test_page";
    }

    @PostMapping("/")
    public String getBibleVerse(@RequestParam("query") String query, Model model) {
        log.info("query:"+query);
        String result = fetchBibleVerse(query);
        log.info("result:"+result);
        model.addAttribute("result", result);
        log.info("결과값: "+result);
        return "test_page";
    }

    private String fetchBibleVerse(String query) {
        String baseUrl = "http://ibibles.net/quote.php?kor-mat/";

        if (query.startsWith("마 ")) {
            query = query.substring(2); // '마 ' 제거

            String url;
            if (query.contains(":")) {
                String[] parts = query.split(":");
                url = baseUrl + parts[0] + ":" + parts[1];
            } else {
                url = baseUrl + query;
            }

            RestTemplate restTemplate = new RestTemplate();
            try {
                String response = restTemplate.getForObject(url, String.class);
                log.info("HTML Response: {}", response); // HTML 응답 로그 출력
                log.info("parseBibleVerse(response)="+parseBibleVerse(response));
                return parseBibleVerse(response);
            } catch (Exception e) {
                log.error("Error fetching Bible verse", e);
                return "성경 구절을 찾을 수 없습니다.";
            }
        } else {
            return "유효한 입력이 아닙니다. '마'로 시작하는 구절을 입력하세요.";
        }
    }

    private String parseBibleVerse(String html) {
        Document doc = Jsoup.parse(html);
        Element body = doc.body();

        StringBuilder sb = new StringBuilder();
        String verseNumber = "";

        for (Element element : body.getAllElements()) {
            if (element.tagName().equals("small")) {
                verseNumber = element.text();
            } else if (element.tagName().equals("br")) {
                // 줄바꿈을 나타내므로 무시
            } else {
                // 구절 번호를 "장"과 "절"로 분리합니다.
                if (!verseNumber.isEmpty()) {
                    String[] parts = verseNumber.split(":");
                    if (parts.length == 2) {
                        sb.append(parts[0]).append("장 ").append(parts[1]).append("절 ").append(element.text()).append("\n");
                    } else {
                        sb.append(verseNumber).append(" ").append(element.text()).append("\n");
                    }
                    verseNumber = ""; // 구절 번호를 초기화합니다.
                } else {
                    sb.append(element.text()).append("\n");
                }
            }
        }

        return sb.toString().trim();
    }

}