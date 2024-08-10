package org.example.service;

import java.util.HashMap;
import java.util.Map;

public class BibleBookMapper {
    private static final Map<String, String> bookMap = new HashMap<>();

    static {
        bookMap.put("창 ", "Gen");
        bookMap.put("출 ", "Exo");
        bookMap.put("레 ", "Lev");
        bookMap.put("민 ", "Num");
        bookMap.put("신 ", "Deu");
        bookMap.put("수 ", "Jos");
        bookMap.put("삿 ", "Jdg");
        bookMap.put("룻 ", "Rut");
        bookMap.put("삼상", "1Sa");
        bookMap.put("삼하", "2Sa");
        bookMap.put("왕상", "1Ki");
        bookMap.put("왕하", "2Ki");
        bookMap.put("대상", "1Ch");
        bookMap.put("대하", "2Ch");
        bookMap.put("스 ", "Ezr");
        bookMap.put("느 ", "Neh");
        bookMap.put("에 ", "Est");
        bookMap.put("욥 ", "Job");
        bookMap.put("시 ", "Psa");
        bookMap.put("잠 ", "Pro");
        bookMap.put("전 ", "Ecc");
        bookMap.put("아 ", "Sos");
        bookMap.put("사 ", "Isa");
        bookMap.put("렘 ", "Jer");
        bookMap.put("애 ", "Lam");
        bookMap.put("겔 ", "Eze");
        bookMap.put("단 ", "Dan");
        bookMap.put("호 ", "Hos");
        bookMap.put("욜 ", "Joe");
        bookMap.put("암 ", "Amo");
        bookMap.put("옵 ", "Oba");
        bookMap.put("욘 ", "Jon");
        bookMap.put("미 ", "Mic");
        bookMap.put("나 ", "Nah");
        bookMap.put("합 ", "Hab");
        bookMap.put("습 ", "Zep");
        bookMap.put("학 ", "Hag");
        bookMap.put("슥 ", "Zec");
        bookMap.put("말 ", "Mal");
        bookMap.put("마 ", "Mat");
        bookMap.put("막 ", "Mar");
        bookMap.put("눅 ", "Luk");
        bookMap.put("요 ", "Joh");
        bookMap.put("행 ", "Act");
        bookMap.put("롬 ", "Rom");
        bookMap.put("고전", "1Co");
        bookMap.put("고후", "2Co");
        bookMap.put("갈 ", "Gal");
        bookMap.put("엡 ", "Eph");
        bookMap.put("빌 ", "Phi");
        bookMap.put("골 ", "Col");
        bookMap.put("살전", "1Th");
        bookMap.put("살후", "2Th");
        bookMap.put("딤전", "1Ti");
        bookMap.put("딤후", "2Ti");
        bookMap.put("딛 ", "Tit");
        bookMap.put("몬 ", "Phm");
        bookMap.put("히 ", "Heb");
        bookMap.put("약 ", "Jam");
        bookMap.put("벧전", "1Pe");
        bookMap.put("벧후", "2Pe");
        bookMap.put("요1", "1Jo");
        bookMap.put("요2", "2Jo");
        bookMap.put("요3", "3Jo");
        bookMap.put("유 ", "Jud");
        bookMap.put("계 ", "Rev");
    }

    public static String getEnglishAbbreviation(String bookName) {
        return bookMap.getOrDefault(bookName, "");
    }

    public static String getBibleUrl(String bookName) {
        String abbreviation = getEnglishAbbreviation(bookName);
        if (!abbreviation.isEmpty()) {
            return "http://ibibles.net/quote.php?kor-" + abbreviation + "/";
        }
        return "";
    }

}
