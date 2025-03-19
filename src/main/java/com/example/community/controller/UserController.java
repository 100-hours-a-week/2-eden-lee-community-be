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

    @GetMapping("/exists")
    public ApiResponse<DuplicationCheckResponseDto> isExist(@RequestParam(required = false) String email,
                                                            @RequestParam(required = false) String nickname) {
        if (email != null) {

        } else if (nickname != null) {

        } else {

        }

        DuplicationCheckResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }
}
