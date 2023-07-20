package com.example.tdd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ArticleControllerTests {
    @InjectMocks
    private ArticleController articleController;
    private MockMvc mockMvc;

    @Test
    public void mockMvcIsNotNull() {
        assertThat(articleController).isNotNull();
        assertThat(mockMvc).isNotNull();
    }
}
