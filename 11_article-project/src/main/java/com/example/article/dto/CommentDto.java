package com.example.article.dto;

import com.example.article.entity.CommentEntity;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;
    private Long articleId;
    private String writer;
    private String content;

    public static CommentDto fromEntity(CommentEntity commentEntity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(commentEntity.getId());
        commentDto.setArticleId(commentEntity.getArticleId());
        commentDto.setWriter(commentEntity.getWriter());
        commentDto.setContent(commentEntity.getContent());

        return commentDto;
    }
}
