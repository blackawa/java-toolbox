package jp.blackawa.javatoolbox.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
public class ScoredTool {
    private Tool tool;
    private Integer score;
}
