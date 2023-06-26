package com.example.article;

import com.example.article.dto.CommentDto;
import com.example.article.entity.CommentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    // 새로운 댓글 엔터티 생성 > repository에 저장 및 반환
    public CommentDto createComment(Long articleId, CommentDto dto) {

        // 해당 article이 존재하지 않는 경우
        if (!articleRepository.existsById(articleId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        
        CommentEntity newComment = new CommentEntity();
        newComment.setWriter(dto.getWriter());
        newComment.setContent(dto.getContent());
        newComment.setArticleId(articleId);

        return CommentDto.fromEntity(commentRepository.save(newComment));
    }

    // 특정 article의 댓글 읽어오기
    public List<CommentDto> readCommentByArticleId(Long articleId) {
        // 방법1: for-each 이용
        List<CommentEntity> commentEntities = commentRepository.findAllByArticleId(articleId);
        List<CommentDto> commentDtoList = new ArrayList<>();

        for (CommentEntity commentEntity : commentEntities) {
            commentDtoList.add(CommentDto.fromEntity(commentEntity));
        }

        return commentDtoList;
        
        // 방법2: stream 이용
        // commentRepository.findAllByArticleId(articleId).stream()
        //         .map(CommentDto::fromEntity).toList();
    }

    // 게시글 댓글 수정
    public CommentDto updateComment(Long articleId, Long commentId, CommentDto commentDto) {
        Optional<CommentEntity> optionalCommentEntity = commentRepository.findAllById(commentId);

        if (!optionalCommentEntity.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity commentEntity = optionalCommentEntity.get();
        
        // 수정할 대상 댓글의 게시글이 url path로 들어온 게시글 Id와 일치하는지 확인
        if (articleId.equals(commentEntity.getArticleId())) {
            commentEntity.setContent(commentDto.getContent());
            commentEntity.setWriter(commentDto.getContent());
            return CommentDto.fromEntity(commentRepository.save(commentEntity));
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    public void deleteComment(Long articleId, Long commentId) {
        Optional<CommentEntity> optionalCommentEntity = commentRepository.findAllById(commentId);

        if (!optionalCommentEntity.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity commentEntity = optionalCommentEntity.get();

        if (articleId.equals(commentEntity.getArticleId())) {
            commentRepository.deleteById(commentId);
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
