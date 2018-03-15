package jp.blackawa.javatoolbox.controller;

import java.util.HashMap;
import java.util.Map;

import jp.blackawa.javatoolbox.service.TagService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "tags")
public class TagController {
    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("")
    public ModelAndView index() {
        Map<String, Object> params = new HashMap<>();
        params.put("tags", tagService.findAll());
        return new ModelAndView("tag/index", params);
    }
}
