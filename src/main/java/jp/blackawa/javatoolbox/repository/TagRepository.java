package jp.blackawa.javatoolbox.repository;

import java.util.List;
import java.util.stream.Collectors;

import jp.blackawa.javatoolbox.entity.Tag;
import org.dom4j.Element;
import org.springframework.stereotype.Repository;

@Repository
public class TagRepository {
    private Dom4jRepository dom4jRepository;

    public TagRepository(Dom4jRepository dom4jRepository) {
        this.dom4jRepository = dom4jRepository;
    }

    public List<Tag> findAll() {
        Element tools = dom4jRepository.findRootElement();
        return ((List<Element>) tools.selectNodes("//tool/tags/tag")).stream()
                .map(Element::getText)
                .distinct()
                .map(s -> new Tag(s))
                .collect(Collectors.toList());
    }
}
