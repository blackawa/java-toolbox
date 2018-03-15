package jp.blackawa.javatoolbox.repository;

import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

@Repository
public class Dom4jRepository {
    public Element findRootElement() {
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(new ClassPathResource("tools.xml").getInputStream());
        } catch (DocumentException | IOException e) {
            throw new RuntimeException("Cannot find datasource", e);
        }
        return document.getRootElement();
    }
}
