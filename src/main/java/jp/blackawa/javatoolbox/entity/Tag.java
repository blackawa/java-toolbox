package jp.blackawa.javatoolbox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.dom4j.Element;

@ToString
@Getter
@AllArgsConstructor
public class Tag {
    private String name;

    public static Tag fromDom4jElement(Element element) {
        return new Tag(element.getText());
    }
}
