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
    public ApiResponse<CommentCreateResponseDto> createComment(@RequestBody CommentCreateRequestDto request) {
        CommentCreateResponseDto data = commentService.createComment(
                request.getUserId(),
                request.getPostId(),
                request.getContents());
        return ApiResponse.onSuccess(data);
    }

    @PatchMapping("/{comment_id}")
    public ApiResponse<CommentUpdateResponseDto> updateComment(@PathVariable("comment_id") Long commentId,
                                                               @RequestBody CommentUpdateRequestDto request) {
        CommentUpdateResponseDto data = commentService.updateComment(
                commentId,
                request.getContents());
        return ApiResponse.onSuccess(data);
    }

    @DeleteMapping("/{comment_id}")
    public ApiResponse<Void> deleteComment(@PathVariable("comment_id") Long commentId) {
        commentService.deleteComment(commentId);

        return ApiResponse.onSuccess(null);
    }
}
