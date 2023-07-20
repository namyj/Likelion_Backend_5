package com.example.tdd.service;

import com.example.tdd.dto.ArticleDto;
import com.example.tdd.entity.Article;
import com.example.tdd.repo.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;

    public List<ArticleDto> findByTitle(String query) {
        List<ArticleDto> articleDtoList = new ArrayList<>();
        for (Article article : articleRepository.findAllByTitleContains(query)) {
            ArticleDto articleDto = new ArticleDto();
            articleDto.setId(article.getId());
            articleDto.setTitle(article.getTitle());
            articleDto.setContent(article.getContent());
            articleDtoList.add(articleDto);
        }

        return articleDtoList;
    }
}
