package com.example.http.dto;


// 블로글 게시글 dto
// 게시글 제목
// 게시글 내용
/*
{
    "title" : "제목",
    "content": "내용"
}
 */
import lombok.Data;

@Data
public class ArticleDto {
    private String title;
    private String content;
}
