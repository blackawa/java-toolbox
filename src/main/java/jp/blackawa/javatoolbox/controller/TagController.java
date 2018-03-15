package jp.blackawa.javatoolbox.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.blackawa.javatoolbox.entity.Tool;
import jp.blackawa.javatoolbox.exception.NotFoundException;
import jp.blackawa.javatoolbox.service.TagService;
import jp.blackawa.javatoolbox.service.ToolService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "tags")
public class TagController {
    private TagService tagService;
    private ToolService toolService;

    public TagController(TagService tagService, ToolService toolService) {
        this.tagService = tagService;
        this.toolService = toolService;
    }

    @GetMapping("")
    public ModelAndView index() {
        Map<String, Object> params = new HashMap<>();
        params.put("tags", tagService.findAll());
        return new ModelAndView("tag/index", params);
    }

    @GetMapping("{tagName}")
    public ModelAndView show(@PathVariable("tagName") String tagName) {
        Map<String, Object> params = new HashMap<>();
        List<Tool> tools = toolService.findByTag(tagName);
        if (tools.isEmpty()) {
            throw new NotFoundException();
        }
        params.put("tools", tools);
        params.put("tagName", tagName);
        return new ModelAndView("tag/show", params);
    }
}
