package com.example.community.controller;

import com.example.community.apiPayload.ApiResponse;
import com.example.community.dto.auth.*;
import com.example.community.dto.user.UserCreateRequestDto;
import com.example.community.dto.user.UserCreateResponseDto;
import com.example.community.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        LoginResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }
}
