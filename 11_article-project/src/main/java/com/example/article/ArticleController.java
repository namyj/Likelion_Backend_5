package com.example.article;

import com.example.article.dto.ArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController // REST API 처리 컨트롤러
@RequestMapping("/articles")
@RequiredArgsConstructor // final 필드들에 대한 생성자 자동 생성
public class ArticleController {
    private final ArticleService service;

    // POST /articles

    @PostMapping
    public ArticleDto create(@RequestBody ArticleDto dto) {
        return service.createArticle(dto);
    }

    // GET /articles
    @GetMapping
    public List<ArticleDto> readAll() {
        return service.readArticleAll();
    }

    // GET /articles/{id}
    @GetMapping("/{id}")
    public ArticleDto read(@PathVariable("id") Long id) {
        return service.readArticle(id);
    }

    // PUT /articles/{id}
    @PutMapping("/{id}")
    public ArticleDto update (
            @PathVariable("id") Long id,
            @RequestBody ArticleDto dto
    ) {
        return service.updateArticle(id, dto);
    }

    // DELETE /articles/{id}
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.deleteArticle(id);
    }
}
