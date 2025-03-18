package com.example.community.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserAuthResponse {
    private Integer userId;
    private String accessToken;
    private String refreshToken;
}
