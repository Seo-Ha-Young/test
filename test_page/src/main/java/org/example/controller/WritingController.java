package org.example.controller;

import org.example.service.BibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WritingController {

    private final BibleService bibleService;

    @Autowired
    public WritingController(BibleService bibleService) {
        this.bibleService = bibleService;
    }

    @GetMapping("/writingPage")
    public String showWritingPage(Model model) {
        String apiResponse = bibleService.getApiResponse();
        model.addAttribute("apiResponse", apiResponse);
        return "writingPage";
    }
}
