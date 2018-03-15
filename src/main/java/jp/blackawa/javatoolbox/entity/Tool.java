package jp.blackawa.javatoolbox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@ToString
@Getter
@AllArgsConstructor
public class Tool {
    private String name;
    private String url;
    private String description;
    private List<Tag> tags;

    public static Tool fromDom4jElement(Element element) {
        String name = element.element("name").getText();
        String url = element.element("url").getText();
        String description = element.element("description").getText();
        List<Tag> tags = ((List<Element>) element.element("tags").elements()).stream()
                .map(Tag::fromDom4jElement)
                .collect(Collectors.toList());
        return new Tool(name, url, description, tags);
    }

    public ScoredTool score(List<Pattern> queries) {
        Integer score = queries.stream()
                .map(q -> {
                    List<String> target = new ArrayList<>();
                    target.add(name);
                    target.add(description);
                    tags.forEach(t -> target.add(t.getName()));
                    int i = 0;
                    Matcher m = q.matcher(String.join(" ", target));
                    while (m.find())
                        i++;
                    return i;
                })
                .reduce((n1, n2) -> n1 + n2)
                .orElse(0);
        return new ScoredTool(this, score);
    }

    public boolean hasTag(Tag tag) {
        return tags.contains(tag);
    }
}
