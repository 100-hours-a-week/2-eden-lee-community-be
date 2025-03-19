package com.example.community.controller;

import com.example.community.apiPayload.ApiResponse;
import com.example.community.dto.post.*;
import com.example.community.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public ApiResponse<PostListReadResponseDto> getPostList() {
        PostListReadResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }

    @GetMapping("/{post_id}")
    public ApiResponse<PostReadResponseDto> getPost(@PathVariable Integer post_id) {
        PostReadResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }

    @PostMapping("")
    public ApiResponse<PostCreateResponseDto> createPost(@RequestBody PostCreateRequestDto postCreateRequest) {
        PostCreateResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }

    @PatchMapping("/{post_id}")
    public ApiResponse<PostUpdateResponseDto> updatePost(@PathVariable Integer post_id,
                                                         @RequestBody PostUpdateRequestDto updateRequest) {
        PostUpdateResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }

    @DeleteMapping("/{post_id}")
    public ApiResponse<Void> deletePost(@PathVariable Integer post_id) {
        return ApiResponse.onSuccess(null);
    }

    @PostMapping("/{post_id}/likes")
    public ApiResponse<LikesToggleResponseDto> togglePostLikes(@PathVariable Integer post_id,
                                                               @RequestBody LikesToggleRequestDto likesToggleRequest) {
        LikesToggleResponseDto data = null;
        return ApiResponse.onSuccess(data);
    }
}
