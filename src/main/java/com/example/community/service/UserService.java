package com.example.community.service;

import com.example.community.apiPayload.code.status.ErrorStatus;
import com.example.community.apiPayload.exception.GeneralException;
import com.example.community.converter.UserConverter;
import com.example.community.domain.User;
import com.example.community.dto.user.*;
import com.example.community.repository.UserRepository;

import com.example.community.util.ImageFileUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ImageFileUploader imageFileUploader;

    @Transactional
    public UserCreateResponseDto createUser(String email,
                                            String password,
                                            String nickname,
                                            MultipartFile profileImage) {
        String profileImageUrl = null;
        try {
            profileImageUrl = imageFileUploader.upload(profileImage);
        } catch (IOException e) {
            throw new GeneralException(ErrorStatus.FILE_UPLOAD_FAIL);
        }

        User user = User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .build();
        userRepository.save(user);

        return UserConverter.toUserCreateResponseDto(user);
    }

    @Transactional(readOnly = true)
    public UserReadResponseDto readUser(Long userId) {
        User user = userRepository.findActiveUserById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        return UserConverter.toUserReadResponseDto(user);
    }

    @Transactional
    public UserUpdateResponseDto updateUser(Long userId,
                                            String nickname,
                                            MultipartFile profileImage) {
        User user = userRepository.findActiveUserById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        String profileImageUrl = null;
        try {
            profileImageUrl = imageFileUploader.upload(profileImage);
        } catch (IOException e) {
            throw new GeneralException(ErrorStatus.FILE_UPLOAD_FAIL);
        }
        user.updateProfile(nickname, profileImageUrl);

        return UserConverter.toUserUpdateResponseDto(user);
    }

    @Transactional
    public PasswordUpdateResponseDto updatePassword(Long userId, String newPassword) {
        User user = userRepository.findActiveUserById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        String oldPassword = user.getPassword();
        if (oldPassword.equals(newPassword)) {
            throw new GeneralException(ErrorStatus.SAME_PASSWORD);
        }
        user.updatePassword(newPassword);

        return UserConverter.toPasswordUpdateResponseDto(user);
    }

    @Transactional
    public Void deleteUser(Long userId) {
        User user = userRepository.findActiveUserById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        user.withdraw();

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
