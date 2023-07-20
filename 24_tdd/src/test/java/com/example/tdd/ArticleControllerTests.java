package com.example.tdd;

import com.example.tdd.controller.ArticleController;
import com.example.tdd.dto.ArticleDto;
import com.example.tdd.service.ArticleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTests {
    @InjectMocks
    private ArticleController articleController;

    @Mock
    private ArticleService articleService;
    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(articleController)
                .build();
    }

    @Test
    public void mockMvcIsNotNull() {
        assertThat(articleController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }

    // URL에 특정 쿼리 파라미터가 포함되어 있을 때,
    // 응답으로 JSON을 반환한다.
    // 그리고 그 JSON으로 표현된 데이터는
    // 게시글 데이터가 있으며, 그 제목은 쿼리 파라미터로 전달한 값을 포함한다.
    @Test
    @DisplayName("ArtilcleController에서 title로 Article 조회하기")
    public void queryArticle() throws Exception {
        // given
        String url = "/articles?title=test";
        ArticleDto articleDto = new ArticleDto();
        articleDto.setTitle("contains test");
        when(articleService.findByTitle("test"))
                .thenReturn(Collections.singletonList(articleDto));

        // when
        ResultActions resultActions = mockMvc.perform(get(url));

        // then
        resultActions.andExpectAll(
                status().is2xxSuccessful(),
                content().contentType(MediaType.APPLICATION_JSON_VALUE),
                jsonPath("$[0].title", containsString("test"))
        );
    }
}
