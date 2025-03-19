package com.example.community.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class SignupRequestDto {
    private String email;
    private String password;
    private String nickname;
    private String profileImage;
}
