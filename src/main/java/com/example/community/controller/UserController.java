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
    public ApiResponse<UserCreateResponseDto> createUser(@RequestBody UserCreateRequestDto userCreateRequestDto) {
        UserCreateResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }

    @GetMapping("/{user_id}")
    public ApiResponse<UserReadResponseDto> getUser(@PathVariable("user_id") Integer user_id) {
        UserReadResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }

    @PatchMapping("/{user_id}/profile")
    public ApiResponse<UserUpdateResponseDto> updateProfile(@PathVariable("user_id") Integer user_id,
                                                            @RequestBody UserUpdateRequestDto userUpdateRequest) {
        UserUpdateResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }

    @PatchMapping("/{user_id}/password")
    public ApiResponse<PasswordUpdateResponseDto> updatePassword(@PathVariable("user_id") Integer user_id,
                                                                 @RequestBody PasswordUpdateRequestDto passwordUpdateRequest) {
        PasswordUpdateResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }

    @DeleteMapping("/{user_id}")
    public ApiResponse<Void> deleteUser(@PathVariable("user_id") Integer user_id) {
        return ApiResponse.onSuccess(null);
    }

    @GetMapping("/email")
    public ApiResponse<DuplicationCheckResponseDto> isEmailTaken(@RequestParam(required = false) String email) {

        DuplicationCheckResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }

    @GetMapping("/nickname")
    public ApiResponse<DuplicationCheckResponseDto> isNicknameTaken(@RequestParam(required = false) String nickname) {

        DuplicationCheckResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }
}
