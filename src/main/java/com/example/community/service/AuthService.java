package com.example.community.service;

import com.example.community.apiPayload.code.status.ErrorStatus;
import com.example.community.apiPayload.exception.GeneralException;
import com.example.community.converter.AuthConverter;
import com.example.community.domain.User;
import com.example.community.dto.auth.LoginResponseDto;
import com.example.community.repository.UserRepository;
import com.example.community.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional(readOnly = true)
    public LoginResponseDto login(String email, String password) {
        User user = userRepository.findActiveUserByEmailAndPassword(email, password)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        if (user.getDeletedAt() != null) {
            throw new GeneralException(ErrorStatus.USER_NOT_FOUND);
        }

        String userId = user.getId().toString();
        String accessToken = jwtTokenProvider.createAccessToken(userId);
        String refreshToken = jwtTokenProvider.createRefreshToken(userId);

        return AuthConverter.toLoginResponseDto(user, accessToken, refreshToken);
    }
}
