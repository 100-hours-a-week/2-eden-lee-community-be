package com.example.community.controller;

import com.example.community.apiPayload.ApiResponse;
import com.example.community.dto.user.*;
import com.example.community.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("")
    public ApiResponse<UserCreateResponseDto> createUser(@ModelAttribute UserCreateRequestDto request) {

        UserCreateResponseDto data = userService.createUser(request.getEmail(),
                request.getPassword(),
                request.getNickname(),
                request.getProfileImage());

        return ApiResponse.onSuccess(data);
    }

    @GetMapping("/{user_id}")
    public ApiResponse<UserReadResponseDto> getUser(@PathVariable("user_id") Long userId) {
        UserReadResponseDto data = userService.readUser(userId);

        return ApiResponse.onSuccess(data);
    }

    @PutMapping("/{user_id}/profile")
    public ApiResponse<UserUpdateResponseDto> updateProfile(@PathVariable("user_id") Long userId,
                                                            @ModelAttribute UserUpdateRequestDto request) {
        UserUpdateResponseDto data = userService.updateUser(
                userId,
                request.getNickname(),
                request.getProfileImage());

        return ApiResponse.onSuccess(data);
    }

    @PutMapping("/{user_id}/password")
    public ApiResponse<PasswordUpdateResponseDto> updatePassword(@PathVariable("user_id") Long userId,
                                                                 @RequestBody PasswordUpdateRequestDto request) {
        PasswordUpdateResponseDto data = userService.updatePassword(userId, request.getPassword());

        return ApiResponse.onSuccess(data);
    }

    @DeleteMapping("/{user_id}")
    public ApiResponse<Void> deleteUser(@PathVariable("user_id") Long userId) {
        Void data = userService.deleteUser(userId);

        return ApiResponse.onSuccess(data);
    }

    @GetMapping("/email")
    public ApiResponse<DuplicationCheckResponseDto> isEmailTaken(@RequestParam(required = false) String email) {
        DuplicationCheckResponseDto data = userService.isEmailTaken(email);

        return ApiResponse.onSuccess(data);
    }

    @GetMapping("/nickname")
    public ApiResponse<DuplicationCheckResponseDto> isNicknameTaken(@RequestParam(required = false) String nickname) {
        DuplicationCheckResponseDto data = userService.isNicknameTaken(nickname);

        return ApiResponse.onSuccess(data);
    }
}
