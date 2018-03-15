package jp.blackawa.javatoolbox.repository;

import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import jp.blackawa.javatoolbox.entity.ScoredTool;
import jp.blackawa.javatoolbox.entity.Tool;
import org.dom4j.Element;
import org.springframework.stereotype.Repository;

@Repository
public class ToolRepository {
    private Dom4jRepository dom4jRepository;

    public ToolRepository(Dom4jRepository dom4jRepository) {
        this.dom4jRepository = dom4jRepository;
    }

    public List<Tool> findByQueries(List<String> queries) {
        Element tools = dom4jRepository.findRootElement();
        List<Pattern> regexQueries = queries.stream().map(q -> Pattern.compile(q, Pattern.CASE_INSENSITIVE)).collect(Collectors.toList());
        return ((List<Element>) tools.elements()).stream()
                .map(Tool::fromDom4jElement)
                .map(tool -> tool.score(regexQueries))
                .sorted(Comparator.comparingInt(ScoredTool::getScore))
                .filter(s -> s.getScore() > 0)
                .map(ScoredTool::getTool)
                .collect(Collectors.toList());
    }
}
