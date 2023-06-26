package com.example.article;

import com.example.article.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
    // 특정 ArticleId에 해당하는 CommentEntity를 반환하는 메소드
    List<CommentEntity> findAllByArticleId(Long id);
}
