package com.example.community.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class LoginResponseDto {
    private Integer userId;
    private String accessToken;
    private String refreshToken;
}
