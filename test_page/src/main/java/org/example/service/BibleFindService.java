package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Log4j2
public class BibleFindService {

    @Autowired
    public FindMaxVerseService findMaxVerseService;

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

    //구절 검색
    public String fetchBibleVerse(String query) {

        log.info("query =="+query);

        String[] name = query.split(" "); //'각 권 명칭' "장 : 절" 분리
        String name2 = name[0]; // 권 명칭 사용

        log.info("구절검색 전체문구 = "+query + "나누어진 문구 = "+name[0]+"||"+name[1]);

        String baseUrl = displayBibleUrl(name2);

        query = name[1]; // 분리한 뒷부분 사용

        log.info("baseUrl? = "+baseUrl);
        log.info("query? = " + query);

        String url;
        //장과 절을 존재할 때에 url
        if (query.contains(":")) {
            String[] parts = query.split(":");
            url = baseUrl + parts[0] + ":" + parts[1];
            // 장만 있을 때에 url
        } else if(query.matches("\\d+")) { //  정규 표현식: 전체 문자열이 숫자로만 구성되어 있는지 확인
            int maxVerse = findMaxVerseService.FindMaxVerse(baseUrl,query);
            url = baseUrl + query + ":" + "1-" +maxVerse;
        }else{
            url = baseUrl + query;
        }

        log.info("로그 파트 "+url);

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
        Elements smartTags = body.select("small");

        StringBuilder sb = new StringBuilder();
        String verseNumber = "";

        for (Element element : body.getAllElements()) {

            log.info("smartTags "+smartTags);
            int count = smartTags.size();
            log.info(("smartTags count = "+count));

            if (element.tagName().equals("small")) {
                verseNumber = element.text();
                log.info("vereNumber = "+verseNumber + "범위 = "+count);
                if (!verseNumber.isEmpty()) { // 검색창에 장 or 장과 절을 입력했다면

                    //장과 절을 ":"를 기준으로 나누기
                    String[] parts = verseNumber.split(":");
                    String part = verseNumber;

                    if(count == 1) { // 한 절만 검색했을 때 장과 절을 표시
                        if (parts.length == 2) { // 장과 절로 나눌 수 있다면
                            sb.replace(0, part.length(), parts[0] + "장 " + parts[1] + "절");
                        }
                        verseNumber= "";
                    } else if(count > 1) { // 한 절 이상을 검색할 때에, n:m 구조를 n장 m절 구조로 변환
                        if (parts.length == 2) {
                            String originalText = sb.toString();
                            String modifiedText = originalText.replaceAll("(\\d+):(\\d+)", "\n$1장 $2절");

                            sb = new StringBuilder(modifiedText);
                        }

                        }


                    log.info("정보 : "+sb);

                }

            } else if (element.tagName().equals("br")) {
                // 줄바꿈을 나타내므로 무시

            } else {
                // 구절 번호를 "장"과 "절"로 분리합니다.
                if (!verseNumber.isEmpty()) {
                    log.info("설마 여기껀 아니지> "+verseNumber);
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
