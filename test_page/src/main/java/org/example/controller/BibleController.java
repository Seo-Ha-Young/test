package org.example.controller;

import lombok.extern.log4j.Log4j2;

import org.example.service.BibleFindService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Log4j2
@Controller
public class BibleController {

    public static BibleFindService bibleFindService;

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
        String result = bibleFindService.fetchBibleVerse(query);
        log.info("result:"+result);
        model.addAttribute("result", result);
        log.info("결과값: "+result);
        return "test_page";
    }

}