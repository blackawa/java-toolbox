package jp.blackawa.javatoolbox.repository;

import jp.blackawa.javatoolbox.entity.Tag;
import jp.blackawa.javatoolbox.entity.Tool;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ToolRepository {
    private TagRepository tagRepository;

    public ToolRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tool> findByQueries(List<String> queries) {
        // xmlをパースしてマッチ度を測る。
        // マッチ度の順に並び替えて、0より大きいものだけ残す。
        SAXReader reader = new SAXReader();
        Document document;
        try {
            String fileName = getClass().getClassLoader().getResource("tools.xml").getFile();
            document = reader.read(new File(fileName));
        } catch (DocumentException e) {
            throw new RuntimeException("Cannot find datasource", e);
        }
        Element tools = document.getRootElement();
        return (List<Tool>) tools.elements().stream()
                .filter(t -> t instanceof Element)
                .map(t -> {
                    Element e = (Element) t;
                    String name = e.element("name").getText();
                    String url = e.element("url").getText();
                    String description = e.element("description").getText();
                    List<Tag> tags = (List<Tag>) e.element("tags").elements().stream()
                            .map(t2 -> tagRepository.fromDom4jElement((Element) t2))
                            .collect(Collectors.toList());
                    return new Tool(name, url, description, tags);
                })
                .collect(Collectors.toList());
    }
}
