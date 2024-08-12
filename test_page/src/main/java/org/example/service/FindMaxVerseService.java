package org.example.service;

import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class FindMaxVerseService {
    public int FindMaxVerse(String baseUrl, String a) {
        String chapter = a; // 원하는 장 번호 입력
        int verse = 1; // 초깃값은 1절부터
        boolean found = true;

        while (found) {
            String url = baseUrl + chapter + ":" + verse;
            try {
                Document doc = Jsoup.connect(url).get();
                // 지정한 절에 대한 내용을 찾을 수 있는지 확인
                if (doc.text().contains("Bible verse not found.")) {
                    found = false;
                } else {
                    verse++; // 절 번호 증가
                }
            } catch (Exception e) {
                System.out.println("Error connecting to the URL: " + e.getMessage());
                found = false; // 오류 발생 시 종료
            }
        }

        // 결과 출력
        System.out.println("Chapter " + chapter + " has " + (verse - 1) + " verses.");
        verse = verse-1;
        return verse;
    }
}
