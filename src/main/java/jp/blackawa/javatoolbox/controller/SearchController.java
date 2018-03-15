package jp.blackawa.javatoolbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "search")
public class SearchController {
    @GetMapping("")
    public String index(@RequestParam(required = false) String query) {
        if (query == null) {
            return "redirect:/";
        }
        return "search/index";
    }
}
