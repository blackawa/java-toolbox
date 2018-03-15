package jp.blackawa.javatoolbox.repository;

import jp.blackawa.javatoolbox.entity.Tag;
import org.dom4j.Element;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepository {
    public Tag fromDom4jElement(Element element) {
        return new Tag(element.getText());
    }
}
