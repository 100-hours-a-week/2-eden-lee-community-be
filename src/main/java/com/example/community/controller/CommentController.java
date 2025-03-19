package com.example.community.controller;

import com.example.community.apiPayload.ApiResponse;
import com.example.community.dto.comment.*;
import com.example.community.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("")
    public ApiResponse<CommentCreateResponseDto> createComment(@RequestBody CommentCreateRequestDto commentCreateRequest) {
        CommentCreateResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }

    @PatchMapping("/{comment_id}")
    public ApiResponse<CommentUpdateResponseDto> updateComment(@PathVariable Integer commentId,
                                                               @RequestBody CommentUpdateRequestDto commentUpdateRequest) {
        CommentUpdateResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }

    @DeleteMapping("/{comment_id}")
    public ApiResponse<Void> deleteComment(@PathVariable Integer commentId) {
        return ApiResponse.onSuccess(null);
    }
}
