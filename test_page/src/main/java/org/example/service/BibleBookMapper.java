package org.example.service;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;
@Log4j2
public class BibleBookMapper {
    private static final Map<String, String> bookMap = new HashMap<>();

    static {
        bookMap.put("창", "Gen");
        bookMap.put("출", "Exo");
        bookMap.put("레", "Lev");
        bookMap.put("민", "Num");
        bookMap.put("신", "Deu");
        bookMap.put("수", "Jos");
        bookMap.put("삿", "Jdg");
        bookMap.put("룻", "Rut");
        bookMap.put("삼상", "1Sa");
        bookMap.put("삼하", "2Sa");
        bookMap.put("왕상", "1Ki");
        bookMap.put("왕하", "2Ki");
        bookMap.put("대상", "1Ch");
        bookMap.put("대하", "2Ch");
        bookMap.put("스", "Ezr");
        bookMap.put("느", "Neh");
        bookMap.put("에", "Est");
        bookMap.put("욥", "Job");
        bookMap.put("시", "Psa");
        bookMap.put("잠", "Pro");
        bookMap.put("전", "Ecc");
        bookMap.put("아", "Sos");
        bookMap.put("사", "Isa");
        bookMap.put("렘", "Jer");
        bookMap.put("애", "Lam");
        bookMap.put("겔", "Eze");
        bookMap.put("단", "Dan");
        bookMap.put("호", "Hos");
        bookMap.put("욜", "Joe");
        bookMap.put("암", "Amo");
        bookMap.put("옵", "Oba");
        bookMap.put("욘", "Jon");
        bookMap.put("미", "Mic");
        bookMap.put("나", "Nah");
        bookMap.put("합", "Hab");
        bookMap.put("습", "Zep");
        bookMap.put("학", "Hag");
        bookMap.put("슥", "Zec");
        bookMap.put("말", "Mal");
        bookMap.put("마", "Mat");
        bookMap.put("막", "Mar");
        bookMap.put("눅", "Luk");
        bookMap.put("요", "Joh");
        bookMap.put("행", "Act");
        bookMap.put("롬", "Rom");
        bookMap.put("고전", "1Co");
        bookMap.put("고후", "2Co");
        bookMap.put("갈", "Gal");
        bookMap.put("엡", "Eph");
        bookMap.put("빌", "Phi");
        bookMap.put("골", "Col");
        bookMap.put("살전", "1Th");
        bookMap.put("살후", "2Th");
        bookMap.put("딤전", "1Ti");
        bookMap.put("딤후", "2Ti");
        bookMap.put("딛", "Tit");
        bookMap.put("몬", "Phm");
        bookMap.put("히", "Heb");
        bookMap.put("약", "Jam");
        bookMap.put("벧전", "1Pe");
        bookMap.put("벧후", "2Pe");
        bookMap.put("요1", "1Jo");
        bookMap.put("요2", "2Jo");
        bookMap.put("요3", "3Jo");
        bookMap.put("유", "Jud");
        bookMap.put("계", "Rev");
        bookMap.put("창세기", "Gen");
        bookMap.put("출애굽기", "Exo");
        bookMap.put("레위기", "Lev");
        bookMap.put("민수기", "Num");
        bookMap.put("신명기", "Deu");
        bookMap.put("여호수아", "Jos");
        bookMap.put("사사기", "Jdg");
        bookMap.put("룻기", "Rut");
        bookMap.put("사무엘상", "1Sa");
        bookMap.put("사무엘하", "2Sa");
        bookMap.put("열왕기상", "1Ki");
        bookMap.put("열왕기하", "2Ki");
        bookMap.put("역대상", "1Ch");
        bookMap.put("역대하", "2Ch");
        bookMap.put("에스라", "Ezr");
        bookMap.put("느헤미야", "Neh");
        bookMap.put("에스더", "Est");
        bookMap.put("욥기", "Job");
        bookMap.put("시편", "Psa");
        bookMap.put("잠언", "Pro");
        bookMap.put("전도서", "Ecc");
        bookMap.put("아가", "Sos");
        bookMap.put("이사야", "Isa");
        bookMap.put("예레미야", "Jer");
        bookMap.put("예레미야애가", "Lam");
        bookMap.put("에스겔", "Eze");
        bookMap.put("다니엘", "Dan");
        bookMap.put("호세아", "Hos");
        bookMap.put("요엘", "Joe");
        bookMap.put("아모스", "Amo");
        bookMap.put("오바다", "Oba");
        bookMap.put("요나", "Jon");
        bookMap.put("미가", "Mic");
        bookMap.put("나훔", "Nah");
        bookMap.put("하박국", "Hab");
        bookMap.put("스바냐", "Zep");
        bookMap.put("학개", "Hag");
        bookMap.put("스가랴", "Zec");
        bookMap.put("말라기", "Mal");
        bookMap.put("마태복음", "Mat");
        bookMap.put("마가복음", "Mar");
        bookMap.put("누가복음", "Luk");
        bookMap.put("요한복음", "Joh");
        bookMap.put("사도행전", "Act");
        bookMap.put("로마서", "Rom");
        bookMap.put("고린도전서", "1Co");
        bookMap.put("고린도후서", "2Co");
        bookMap.put("갈라디아서", "Gal");
        bookMap.put("에베소서", "Eph");
        bookMap.put("빌립보서", "Phi");
        bookMap.put("골로새서", "Col");
        bookMap.put("데살로니가전서", "1Th");
        bookMap.put("데살로니가후서", "2Th");
        bookMap.put("디모데전서", "1Ti");
        bookMap.put("디모데후서", "2Ti");
        bookMap.put("디도서", "Tit");
        bookMap.put("빌레몬서", "Phm");
        bookMap.put("히브리서", "Heb");
        bookMap.put("야고보서", "Jam");
        bookMap.put("베드로전서", "1Pe");
        bookMap.put("베드로후서", "2Pe");
        bookMap.put("요한1서", "1Jo");
        bookMap.put("요한2서", "2Jo");
        bookMap.put("요한3서", "3Jo");
        bookMap.put("유다서", "Jud");
        bookMap.put("요한계시록", "Rev");
    }

    public static String getEnglishAbbreviation(String bookName) {
        log.info("bookname = "+bookName);
        log.info("bookname returns = "+bookMap.getOrDefault(bookName, ""));
        return bookMap.getOrDefault(bookName, "");
    }

    public static String getBibleUrl(String bookName) {
        log.info("return name = "+bookName);
        String abbreviation = getEnglishAbbreviation(bookName);
        log.info("abbreviation = "+abbreviation);
        if (!abbreviation.isEmpty()) {
            return "http://ibibles.net/quote.php?kor-" + abbreviation + "/";
        }
        return "";
    }

}
