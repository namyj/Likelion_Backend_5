package com.example.contents;

import com.example.contents.dto.UserDto;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    @DisplayName("UserDto를 나타내는 JSON 요청을 보내면 id가 null이 아닌 UserDto JSON 응답")
    public void testCreat() throws Exception {
        // given
        String username = "kim";
        UserDto requestDto = new UserDto();
        requestDto.setUsername(username);

        Long userId = 1L;
        UserDto responseDto = new UserDto();
        responseDto.setId(userId);
        responseDto.setUsername(requestDto.getUsername());

        Mockito.when(userService.createUser(requestDto))
                .thenReturn(responseDto);

        // when
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .content(JsonUtil.toJson(requestDto))
                        .contentType(MediaType.APPLICATION_JSON));

        // then
        result.andExpectAll(
                MockMvcResultMatchers.status().is2xxSuccessful(), // 상태코드 200
                MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON), // 응답은 Json 형태
                MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(username)), // username은 요청한 값 그대로
                MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.notNullValue()) // id는 null이 아닌 값
        );
    }
}
