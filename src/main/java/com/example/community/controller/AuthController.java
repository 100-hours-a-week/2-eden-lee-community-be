package com.example.community.controller;

import com.example.community.apiPayload.ApiResponse;
import com.example.community.dto.auth.*;
import com.example.community.repository.TestRepository;
import com.example.community.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<SignupResponseDto> signup(@RequestBody SignupRequestDto signupRequest) {
        SignupResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        LoginResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }
}
