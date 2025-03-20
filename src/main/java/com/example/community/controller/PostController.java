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
        PostListReadResponseDto data = postService.getAllPosts();
        return ApiResponse.onSuccess(data);
    }

    @GetMapping("/{post_id}")
    public ApiResponse<PostReadResponseDto> getPost(@PathVariable Long postId) {
        PostReadResponseDto data = postService.getPost(postId);
        return ApiResponse.onSuccess(data);
    }

    @PostMapping("")
    public ApiResponse<PostCreateResponseDto> createPost(@RequestBody PostCreateRequestDto request) {
        PostCreateResponseDto data = postService.createPost(request);
        return ApiResponse.onSuccess(data);
    }

    @PatchMapping("/{post_id}")
    public ApiResponse<PostUpdateResponseDto> updatePost(@PathVariable Long postId,
                                                         @RequestBody PostUpdateRequestDto request) {
        PostUpdateResponseDto data = postService.updatePost(postId, request);
        return ApiResponse.onSuccess(data);
    }

    @DeleteMapping("/{post_id}")
    public ApiResponse<Void> deletePost(@PathVariable Long post_id) {
        postService.deletePost(post_id);
        return ApiResponse.onSuccess(null);
    }

    @PostMapping("/{post_id}/likes")
    public ApiResponse<LikesToggleResponseDto> togglePostLikes(@PathVariable Long postId,
                                                               @RequestBody LikesToggleRequestDto request) {
        LikesToggleResponseDto data = postService.togglePostLikes(postId, request.getUserId());
        return ApiResponse.onSuccess(data);
    }
}
