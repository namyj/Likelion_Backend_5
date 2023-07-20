package com.example.tdd;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class ArticleRepositoryTests {
    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void articleRepositoryIsNotNull() {
        assertThat(articleRepository).isNotNull();
    }

    @Test
    @DisplayName("Article 생성")
    public void createArticle() {
        // given
        Article newArticle = new Article();
        newArticle.setTitle("Hello!");
        newArticle.setContent("Hello world!");

        // when
        Article article = articleRepository.save(newArticle);

        // then
        assertThat(article).isNotNull();
        assertThat(article.getId()).isNotNull();
    }

    @Test
    @DisplayName("Article 제목으로 조회")
    public void findByTitleContains() {
        // given
        Article article = new Article();
        article.setTitle("Hello");
        articleRepository.save(article);

        article = new Article();
        article.setTitle("Yellow");
        articleRepository.save(article);

        // when
        List<Article> result1 = articleRepository.findAllByTitleContains("ell");
        List<Article> result2 = articleRepository.findAllByTitleContains("He");
        List<Article> result3 = articleRepository.findAllByTitleContains("A");

        // then
        assertEquals(2, result1.size());
        assertEquals(1, result1.size());
        assertEquals(0, result1.size());
    }

}
