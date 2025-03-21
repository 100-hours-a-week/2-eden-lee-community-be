package com.example.community.converter;

import com.example.community.domain.User;
import com.example.community.dto.auth.LoginResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthConverter {

    public static LoginResponseDto toLoginResponseDto(User user,
                                                      String accessToken,
                                                      String refreshToken) {
        return LoginResponseDto.builder()
                .userId(user.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
