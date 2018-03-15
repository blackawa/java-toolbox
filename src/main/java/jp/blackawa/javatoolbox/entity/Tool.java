package jp.blackawa.javatoolbox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Tool {
    private String name;
    private String url;
    private String description;
    private List<Tag> tags;
}
