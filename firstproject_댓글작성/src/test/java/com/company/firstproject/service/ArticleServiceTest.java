package com.company.firstproject.service;

import com.company.firstproject.dto.ArticleForm;
import com.company.firstproject.entity.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {

    @Autowired
    ArticleService articleService;

    @Test
    void index() {
        // 1. 예상 데이터 - given
        Article a = new Article(1L, "가가가가", "1111");
        Article b = new Article(2L, "나나나나", "2222");
        Article c = new Article(3L, "다다다다", "3333");
        List<Article> expected = new ArrayList<>(Arrays.asList(a,b,c));

        // 2. 실제 데이터 - when
        List<Article> articles = articleService.index();

        /*
         3. 비교 및 검증 - then
        assertEquals(expected.toString(), articles.toString());
        */
        assertThat(expected.toString().equals(articles.toString()));
    }

    @Test
    void show_성공_존재하는_id_입력() {
        // 1. 예상 데이터 - given
        Long id = 1L;
        Article expected = new Article(id, "가가가가", "1111");

        // 2. 실제 데이터 - when
        Article article = articleService.show(id);

        // 3. 비교 및 검증 - then
        assertEquals(expected.toString(), article.toString());;
    }

    @Test
    void show_실패_존재하지_않는_id_입력() {
        // 1. 예상 데이터 - given
        Long id = -1L;
        Article expected = null;
        // 2. 실제 데이터 - when
        Article article = articleService.show(id);
        // 3. 비교 및 검증 - then
        assertEquals(expected, article);
    }

    @Test
    void create_성공_title과_content만_있는_dto_입력() {

        // 1. 예상 데이터 - given
        String title = "라라라라";
        String content = "44444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        // 2. 실제 데이터 - when
        Article article = articleService.create(dto);

        // 3. 비교 및 검증 - then
        assertEquals(expected.toString(), article.toString());

    }


}