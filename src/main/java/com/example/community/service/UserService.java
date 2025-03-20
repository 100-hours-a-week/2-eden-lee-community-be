package com.example.community.service;

import com.example.community.apiPayload.code.status.ErrorStatus;
import com.example.community.apiPayload.exception.handler.TempHandler;
import com.example.community.converter.UserConverter;
import com.example.community.domain.User;
import com.example.community.dto.user.*;
import com.example.community.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserCreateResponseDto createUser(String email,
                                            String password,
                                            String nickname,
                                            String profileImageUrl) {
        User user = User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .build();
        userRepository.save(user);

        UserCreateResponseDto response = UserConverter.toUserCreateResponseDto(user);
        return response;
    }

    @Transactional(readOnly = true)
    public UserReadResponseDto readUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.USER_NOT_FOUND));

        UserReadResponseDto response = UserConverter.toUserReadResponseDto(user);

        return response;
    }

    @Transactional
    public UserUpdateResponseDto updateUser(Long userId,
                                            String nickname,
                                            String profileImageUrl) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.USER_NOT_FOUND));
        user.updateProfile(nickname, profileImageUrl);

        return UserConverter.toUserUpdateResponseDto(user);
    }

    @Transactional
    public PasswordUpdateResponseDto updatePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.USER_NOT_FOUND));

        String oldPassword = user.getPassword();
        if (oldPassword.equals(newPassword)) {
            throw new TempHandler(ErrorStatus.SAME_PASSWORD);
        }

        user.updatePassword(newPassword);

        return UserConverter.toPasswordUpdateResponseDto(user);
    }

    @Transactional
    public Void deleteUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.USER_NOT_FOUND));
        user.delete();

        return null;
    }

    @Transactional(readOnly = true)
    public DuplicationCheckResponseDto isEmailTaken(String email) {
        Optional<User> user = userRepository.findActiveUserByEmail(email);

        if (user.isEmpty()) {
            return UserConverter.toDuplicationCheckResponseDto(false);
        } else {
            return UserConverter.toDuplicationCheckResponseDto(true);
        }
    }

    @Transactional(readOnly = true)
    public DuplicationCheckResponseDto isNicknameTaken(String nickname) {
        Optional<User> user = userRepository.findActiveUserByNickname(nickname);

        if (user.isEmpty()) {
            return UserConverter.toDuplicationCheckResponseDto(false);
        } else {
            return UserConverter.toDuplicationCheckResponseDto(true);
        }
    }
}
