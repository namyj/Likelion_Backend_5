package com.example.article;

import com.example.article.dto.ArticleDto;
import com.example.article.entity.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository repository;

    // 새로운 article 생성 > repository에 저장, 반환
    public ArticleDto createArticle(ArticleDto dto) {

        ArticleEntity newArticle = new ArticleEntity();
        newArticle.setTitle(dto.getTitle());
        newArticle.setContent(dto.getContent());
        newArticle.setWriter(dto.getWriter());
        return ArticleDto.fromEntity(repository.save(newArticle));
    }

    public ArticleDto readArticle(Long id) {
        Optional<ArticleEntity> optionalArticle = repository.findById(id);

        if (optionalArticle.isPresent()) {
            return ArticleDto.fromEntity(optionalArticle.get());
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public List<ArticleDto> readArticleAll() {
        List<ArticleDto> articleList = new ArrayList<>();

        for (ArticleEntity articleEntity : repository.findAll()) {
            articleList.add(ArticleDto.fromEntity(articleEntity));
        }

        return articleList;
    }

    public ArticleDto updateArticle(Long id, ArticleDto dto) {
        Optional<ArticleEntity> optionalArticle = repository.findById(id);

        if (optionalArticle.isPresent()) {
            ArticleEntity articleEntity = optionalArticle.get();

            articleEntity.setTitle(dto.getTitle());
            articleEntity.setWriter(dto.getWriter());
            articleEntity.setContent(dto.getContent());
            repository.save(articleEntity);
            return ArticleDto.fromEntity(articleEntity);
        } else throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    public void deleteArticle(Long id) {
        Optional<ArticleEntity> optionalArticle = repository.findById(id);

        if (optionalArticle.isPresent()) {
            repository.delete(optionalArticle.get());
        } else throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
    }

    // 페이징 메서드
    public Page<ArticleDto> readArticlePages(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<ArticleEntity> articleEntityPage = repository.findAll(pageable);

        Page<ArticleDto> articleDtoPage = articleEntityPage.map(ArticleDto::fromEntity);
        return articleDtoPage;
    }

    public Page<ArticleDto> searchArticle(String query, Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 20, Sort.by("id").descending());
        return repository.findAllByTitleContains(query, pageable)
                .map(ArticleDto::fromEntity);
    }
}
