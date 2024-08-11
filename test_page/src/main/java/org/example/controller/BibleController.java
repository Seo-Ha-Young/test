package org.example.controller;

import lombok.extern.log4j.Log4j2;

import org.example.service.BibleBookMapper;
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

    public String displayBibleUrl(String bookName) {
        log.info("bookname = "+bookName);
        // 입력된 책 이름을 통해 URL을 가져옵니다.
        String bibleUrl = BibleBookMapper.getBibleUrl(bookName);

        log.info("bibleurl : "+bibleUrl);
        if (!bibleUrl.isEmpty()) {
            System.out.println("해당 성경 구절의 URL: " + bibleUrl);
        } else {
            System.out.println("유효하지 않은 성경 책 이름입니다.");
        }

        return bibleUrl;
    }

    @GetMapping("/")
    public String index() {
        return "test_page";
    }

    @PostMapping("/")
    public String getBibleVerse(@RequestParam("query") String query, Model model) {
        String[] name = query.split(" ");
        String name2 = name[0];
        log.info("name2 : "+name2);
        log.info("query:"+query);
        log.info("url:"+displayBibleUrl(name2));
        String result = fetchBibleVerse(query);
        log.info("result:"+result);
        model.addAttribute("result", result);
        log.info("결과값: "+result);
        return "test_page";
    }

    private String fetchBibleVerse(String query) {

        String[] name = query.split(" "); //'각 권 명칭' "장 : 절" 분리
        String name2 = name[0]; // 권 명칭 사용

        String baseUrl = displayBibleUrl(name2);

        query = name[1]; // 분리한 뒷부분 사용

        log.info("baseUrl? = "+baseUrl);
        log.info("query? = " + query);

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

    }

    private String parseBibleVerse(String html) {
        Document doc = Jsoup.parse(html);
        Element body = doc.body();

        StringBuilder sb = new StringBuilder();
        String verseNumber = "";

        for (Element element : body.getAllElements()) {
            if (element.tagName().equals("small")) {
                verseNumber = element.text();

                if (!verseNumber.isEmpty()) {
                    String[] parts = verseNumber.split(":");
                    String part = verseNumber;
                    if (parts.length == 2) {
                        sb.replace(0,part.length(),parts[0]+"장 "+parts[1]+"절");
                    }
                    log.info("정보 : "+sb);
                   verseNumber = ""; // 구절 번호를 초기화합니다.
                }

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