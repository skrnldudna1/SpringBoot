package com.company.firstproject.dto;

import com.company.firstproject.entity.Article;
import lombok.*;

//@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String content;

    public Article toNoEnity() {
        return new Article(null, title, content);
    }
    public Article toEnity() {
        return new Article(id, title, content);
    }
}

