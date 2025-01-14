package com.company.firstproject.entity;

import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.ClassGenerator;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;


    private String content;

    public void patch(Article article) {
        if (article.title !=null)
            this.title = article.title;
        if (article.content !=null)
            this.content = article.content;

    }
}
