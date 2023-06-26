package com.example.article;

import com.example.article.dto.CommentDto;
import com.example.article.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    // 새로운 댓글 엔터티 생성 > repository에 저장 및 반환
    public CommentDto createComment(Long articleId, CommentDto dto) {
        CommentEntity newComment = new CommentEntity();
        newComment.setWriter(dto.getWriter());
        newComment.setContent(dto.getContent());
        newComment.setArticleId(articleId);

        return CommentDto.fromEntity(commentRepository.save(newComment));

    }
}
