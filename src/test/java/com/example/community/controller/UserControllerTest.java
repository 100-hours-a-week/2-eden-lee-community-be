package com.example.community.controller;

import com.example.community.dto.user.UserCreateRequestDto;
import com.example.community.dto.user.UserCreateResponseDto;
import com.example.community.dto.user.UserReadResponseDto;
import com.example.community.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 회원가입_성공() throws Exception {
        MockMultipartFile profileImage = new MockMultipartFile(
                "profileImage",
                "profile.png",
                "image/png",
                "이미지데이터".getBytes()
        );

        UserCreateRequestDto requestDto = new UserCreateRequestDto("test1234@naver.com", "Test0827!!", "닉네임", profileImage);
        UserCreateResponseDto responseDto = new UserCreateResponseDto(1L);

        given(userService.createUser(
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getNickname(),
                profileImage
        )).willReturn(responseDto);

        mockMvc.perform(multipart("/users")
                .file(profileImage)
                .param("email", requestDto.getEmail())
                .param("password", requestDto.getPassword())
                .param("nickname", requestDto.getNickname()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result.user_id").value(1));

        verify(userService).createUser(
                requestDto.getEmail(),
                requestDto.getPassword(),
                requestDto.getNickname(),
                profileImage
        );
    }

    @Test
    void 사용자_조회_성공() throws Exception {
        Long userId = 1L;
        UserReadResponseDto responseDto = new UserReadResponseDto("test1234@naver.com", "testUSer", "/uploads/image.jpg");

        given(userService.readUser(userId)).willReturn(responseDto);

        mockMvc.perform(get("/users/{user_id}", userId))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.result.email").value("test1234@naver.com"))
            .andExpect(jsonPath("$.result.nickname").value("testUSer"))
            .andExpect(jsonPath("$.result.profile_image_url").value("/uploads/image.jpg"));

        verify(userService).readUser(userId);
    }
}
