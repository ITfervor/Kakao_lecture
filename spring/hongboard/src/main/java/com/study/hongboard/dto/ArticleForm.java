package com.study.hongboard.dto;

import com.study.hongboard.entity.Article;
import lombok.*;


@AllArgsConstructor
@ToString
public class ArticleForm {
    public Long id;
    public String title;
    public String content;

    public Article toEntity() {

        return new Article( id, title, content);
    }
}
