package com.example.community.controller;

import com.example.community.dto.auth.LoginRequestDto;
import com.example.community.dto.auth.LoginResponseDto;
import com.example.community.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 로그인_테스트() throws Exception {
        // given
        LoginRequestDto requestDto = new LoginRequestDto("test1234@naver.com", "Test0827!!");
        LoginResponseDto responseDto = new LoginResponseDto(1L, "access-token", "refresh-token");

        given(authService.login(requestDto.getEmail(), requestDto.getPassword()))
                .willReturn(responseDto);

        // when & then
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result.user_id").value(1L))
                .andExpect(jsonPath("$.result.access_token").value("access-token"))
                .andExpect(jsonPath("$.result.refresh_token").value("refresh-token"));

        verify(authService).login("test1234@naver.com", "Test0827!!");
    }
}
