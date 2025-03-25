package com.example.community.dto.user;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequestDto {
    private String email;
    private String password;
    private String nickname;
    private MultipartFile profileImage;
}
