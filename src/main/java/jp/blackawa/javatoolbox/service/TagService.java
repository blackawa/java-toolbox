package jp.blackawa.javatoolbox.service;

import java.util.List;

import jp.blackawa.javatoolbox.entity.Tag;
import jp.blackawa.javatoolbox.repository.TagRepository;
import org.springframework.stereotype.Service;

@Service
public class TagService {
    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }
}
