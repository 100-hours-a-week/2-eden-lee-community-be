package com.example.community.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserReadResponseDto {
    private String email;
    private String nickname;
    private String profileImage;
}
