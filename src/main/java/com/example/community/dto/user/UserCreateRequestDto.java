package com.example.community.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserCreateRequestDto {
    private String email;
    private String password;
    private String nickname;
    private String profileImage;
}
