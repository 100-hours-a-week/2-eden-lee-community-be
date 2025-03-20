package com.example.community.converter;

import com.example.community.domain.User;
import com.example.community.dto.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserConverter {

    public static UserCreateResponseDto toUserCreateResponseDto(User user) {
        return UserCreateResponseDto.builder()
                .userId(user.getId())
                .build();
    }

    public static UserReadResponseDto toUserReadResponseDto(User user) {
        return UserReadResponseDto.builder()
                .email(user.getEmail())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }

    public static UserUpdateResponseDto toUserUpdateResponseDto(User user) {
        return UserUpdateResponseDto.builder()
                .userId(user.getId())
                .build();
    }

    public static PasswordUpdateResponseDto toPasswordUpdateResponseDto(User user) {
        return PasswordUpdateResponseDto.builder()
                .userId(user.getId())
                .build();
    }

    public static DuplicationCheckResponseDto toDuplicationCheckResponseDto(boolean isDuplicate) {
        return DuplicationCheckResponseDto.builder()
                .isDuplicate(isDuplicate)
                .build();
    }
}
