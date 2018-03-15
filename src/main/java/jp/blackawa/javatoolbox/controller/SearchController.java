package jp.blackawa.javatoolbox.controller;

import jp.blackawa.javatoolbox.entity.Tool;
import jp.blackawa.javatoolbox.service.ToolService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "search")
public class SearchController {
    private ToolService toolService;

    public SearchController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping("")
    public ModelAndView index(@RequestParam(required = false) String query) {
        if (query == null) {
            return new ModelAndView("redirect:/");
        }
        List<Tool> tools = toolService.findByQuery(query);
        Map<String, Object> params = new HashMap<>();
        params.put("tools", tools);
        params.put("query", query);
        return new ModelAndView("search/index", params);
    }
}
