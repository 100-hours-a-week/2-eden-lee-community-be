package com.example.community.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class UserUpdateRequestDto {
    private String nickname;
    private MultipartFile profileImage;
}
