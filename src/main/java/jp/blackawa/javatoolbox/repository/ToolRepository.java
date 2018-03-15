package jp.blackawa.javatoolbox.repository;

import jp.blackawa.javatoolbox.entity.ScoredTool;
import jp.blackawa.javatoolbox.entity.Tool;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Repository
public class ToolRepository {
    public List<Tool> findByQueries(List<String> queries) {
        // xmlをパースしてマッチ度を測る。
        // マッチ度の順に並び替えて、0より大きいものだけ残す。
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(new ClassPathResource("tools.xml").getInputStream());
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Cannot find datasource", e);
        }
        Element tools = document.getRootElement();
        List<Pattern> regexQueries = queries.stream().map(Pattern::compile).collect(Collectors.toList());
        return ((List<Element>) tools.elements()).stream()
                .map(Tool::fromDom4jElement)
                .map(tool -> tool.score(regexQueries))
                .sorted(Comparator.comparingInt(ScoredTool::getScore))
                .filter(s -> s.getScore() > 0)
                .map(ScoredTool::getTool)
                .collect(Collectors.toList());
    }
}
