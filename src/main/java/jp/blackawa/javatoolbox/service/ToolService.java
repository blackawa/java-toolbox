package jp.blackawa.javatoolbox.service;

import jp.blackawa.javatoolbox.entity.Tool;
import jp.blackawa.javatoolbox.repository.ToolRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ToolService {
    private ToolRepository toolRepository;

    public ToolService(ToolRepository toolRepository) {
        this.toolRepository = toolRepository;
    }

    public List<Tool> findByQuery(String query) {
        List<String> queries = Arrays.asList(query.split("\\s"));
        return toolRepository.findByQueries(queries);
    }

    public List<Tool> findByTag(String tagName) {
        return toolRepository.findByTag(tagName);
    }
}
