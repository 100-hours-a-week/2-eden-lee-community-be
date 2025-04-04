package com.example.community.controller;

import com.example.community.apiPayload.ApiResponse;
import com.example.community.domain.User;
import com.example.community.dto.post.*;
import com.example.community.security.CustomUserDetails;
import com.example.community.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("")
    public ApiResponse<PostListReadResponseDto> getPostList() {
        PostListReadResponseDto data = postService.getAllPosts();
        return ApiResponse.onSuccess(data);
    }

    @GetMapping("/{post_id}")
    public ApiResponse<PostReadResponseDto> getPost(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                    @PathVariable("post_id") Long postId) {
        PostReadResponseDto data = postService.getPost(postId, userDetails.getUser());
        return ApiResponse.onSuccess(data);
    }

    @PostMapping("")
    public ApiResponse<PostCreateResponseDto> createPost(@ModelAttribute PostCreateRequestDto request) {
        PostCreateResponseDto data = postService.createPost(request);
        return ApiResponse.onSuccess(data);
    }

    @PutMapping("/{post_id}")
    public ApiResponse<PostUpdateResponseDto> updatePost(@PathVariable("post_id") Long postId,
                                                         @ModelAttribute PostUpdateRequestDto request) {
        PostUpdateResponseDto data = postService.updatePost(postId, request);
        return ApiResponse.onSuccess(data);
    }

    @DeleteMapping("/{post_id}")
    public ApiResponse<Void> deletePost(@PathVariable("post_id") Long postId) {
        postService.deletePost(postId);
        return ApiResponse.onSuccess(null);
    }

    @PostMapping("/{post_id}/likes")
    public ApiResponse<LikesToggleResponseDto> togglePostLikes(@PathVariable("post_id") Long postId,
                                                               @RequestBody LikesToggleRequestDto request) {
        LikesToggleResponseDto data = postService.togglePostLikes(postId, request.getUserId());
        return ApiResponse.onSuccess(data);
    }

    @PatchMapping("/{post_id}/views")
    public ApiResponse<Void> increaseViews(@PathVariable("post_id") Long postId) {
        postService.increaseViews(postId);

        return ApiResponse.onSuccess(null);
    }
}
