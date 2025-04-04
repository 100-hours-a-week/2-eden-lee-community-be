package com.example.community.service;

import com.example.community.apiPayload.code.status.ErrorStatus;
import com.example.community.apiPayload.exception.GeneralException;
import com.example.community.converter.PostConverter;
import com.example.community.domain.Post;
import com.example.community.domain.PostLike;
import com.example.community.domain.User;
import com.example.community.domain.enums.PostLikeStatus;
import com.example.community.dto.post.*;
import com.example.community.dto.post.internal.ContentDto;
import com.example.community.repository.PostLikeRepository;
import com.example.community.repository.PostRepository;
import com.example.community.repository.UserRepository;
import com.example.community.util.ImageFileUploader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;
    private final ImageFileUploader imageFileUploader;

    @Transactional(readOnly = true)
    public PostListReadResponseDto getAllPosts() {
        List<Post> posts = postRepository.findAllActivePosts();

        return PostConverter.toPostListReadResponseDto(posts);
    }

    @Transactional(readOnly = true)
    public PostReadResponseDto getPost(Long postId, User user) {
        Post post = postRepository.findActivePostById(postId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        String likesStatus = post.getUserLikeStatus(user.getId()).toString();

        return PostConverter.toPostReadResponseDto(post, likesStatus);
    }

    @Transactional
    public PostCreateResponseDto createPost(PostCreateRequestDto postDto) {
        User user = userRepository.findActiveUserById(postDto.getUserId())
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        String postImageUrl = null;
        try {
            postImageUrl = imageFileUploader.upload(postDto.getPostImage());
        } catch (IOException e) {
            throw new GeneralException(ErrorStatus.FILE_UPLOAD_FAIL);
        }

        Post post = Post.builder()
                .title(postDto.getTitle())
                .text(postDto.getText())
                .imageUrl(postImageUrl)
                .user(user)
                .views(0)
                .build();
        postRepository.save(post);

        return PostConverter.toPostCreateResponseDto(post);
    }

    @Transactional
    public PostUpdateResponseDto updatePost(Long postId, PostUpdateRequestDto postDto) {
        Post post = postRepository.findActivePostById(postId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

        String postImageUrl = null;
        try {
            postImageUrl = imageFileUploader.upload(postDto.getPostImage());
        } catch (IOException e) {
            throw new GeneralException(ErrorStatus.FILE_UPLOAD_FAIL);
        }

        post.updateTitle(postDto.getTitle());
        post.updateContents(postDto.getText(), postImageUrl);

        return PostConverter.toPostUpdateResponseDto(post);
    }

    @Transactional
    public Void deletePost(Long postId) {
        Post post = postRepository.findActivePostById(postId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));
        post.delete();

        return null;
    }

    @Transactional
    public LikesToggleResponseDto togglePostLikes(Long postId, Long userId) {

        PostLike postLike = postLikeRepository.findByPostIdAndUserId(postId, userId).orElse(null);

        if (postLike == null) {
            // 아직 좋아요를 누른 적이 없어서 null이 반환됨
            Post post = postRepository.findActivePostById(postId)
                    .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));

            User user = userRepository.findActiveUserById(userId)
                    .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

            postLike = PostLike.builder()
                    .post(post)
                    .user(user)
                    .status(PostLikeStatus.ACTIVE)
                    .build();
            postLikeRepository.save(postLike);
        } else if (postLike.getStatus() == PostLikeStatus.INACTIVE) {
            postLike.activate();
        } else if (postLike.getStatus() == PostLikeStatus.ACTIVE) {
            postLike.deactivate();
        }

        Post post = postRepository.findActivePostById(postId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));
        Integer likeCount = post.getLikeCount();

        return PostConverter.toLikesToggleResponseDto(postLike, likeCount);
    }

    @Transactional
    public Void increaseViews(Long postId) {
        Post post = postRepository.findActivePostById(postId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.POST_NOT_FOUND));
        post.increaseViews();
        
        return null;
    }
}
