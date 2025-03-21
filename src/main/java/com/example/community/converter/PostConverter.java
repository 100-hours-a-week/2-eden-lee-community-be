package com.example.community.converter;

import com.example.community.domain.Comment;
import com.example.community.domain.Post;
import com.example.community.domain.PostLike;
import com.example.community.domain.User;
import com.example.community.dto.post.*;
import com.example.community.dto.post.internal.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostConverter {

    public static PostListReadResponseDto toPostListReadResponseDto(List<Post> posts) {
        List<PostMetaDto> postMetaDtoList = new ArrayList<>();
        for (Post post : posts) {
            User user = post.getUser();
            AuthorDto authorDto = AuthorDto.builder()
                    .userId(user.getId())
                    .nickname(user.getNickname())
                    .profileImageUrl(user.getProfileImageUrl())
                    .build();

            CountDto countDto = CountDto.builder()
                    .likes(post.getLikeCount())
                    .comments(post.getCommentCount())
                    .views(post.getViews())
                    .build();

            PostMetaDto postMetaDto = PostMetaDto.builder()
                    .postId(post.getId())
                    .title(post.getTitle())
                    .author(authorDto)
                    .counts(countDto)
                    .createdAt(post.getCreatedAt())
                    .build();
            postMetaDtoList.add(postMetaDto);
        }

        return PostListReadResponseDto.builder()
                .posts(postMetaDtoList)
                .totalCount(postMetaDtoList.size()).build();
    }

    public static PostReadResponseDto toPostReadResponseDto(Post post) {
        ContentDto contentDto = ContentDto.builder()
                .text(post.getText())
                .imageUrl(post.getImageUrl())
                .build();

        User user = post.getUser();
        AuthorDto authorDto = AuthorDto.builder()
                .userId(user.getId())
                .nickname(user.getNickname())
                .profileImageUrl(user.getProfileImageUrl())
                .build();

        CountDto countDto = CountDto.builder()
                .likes(post.getLikeCount())
                .comments(post.getCommentCount())
                .views(post.getViews())
                .build();

        List<Comment> commentList = post.getCommentList();
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : commentList) {
            User commentUser = comment.getUser();
            AuthorDto commentAuthorDto = AuthorDto.builder()
                    .userId(commentUser.getId())
                    .nickname(commentUser.getNickname())
                    .profileImageUrl(commentUser.getProfileImageUrl())
                    .build();

            CommentDto commentDto = CommentDto.builder()
                    .commentId(comment.getId())
                    .contents(comment.getContents())
                    .author(commentAuthorDto)
                    .createdAt(comment.getCreatedAt())
                    .build();
            commentDtoList.add(commentDto);
        }

        String likeStatus = post.getUserLikeStatus(user.getId()).toString();

        PostDto postDto = PostDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .contents(contentDto)
                .author(authorDto)
                .counts(countDto)
                .comments(commentDtoList)
                .likeStatus(likeStatus)
                .createdAt(post.getCreatedAt())
                .build();

        return PostReadResponseDto.builder()
                .post(postDto)
                .build();
    }

    public static PostCreateResponseDto toPostCreateResponseDto(Post post) {
        return PostCreateResponseDto.builder()
                .postId(post.getId())
                .build();
    }

    public static PostUpdateResponseDto toPostUpdateResponseDto(Post post) {
        return PostUpdateResponseDto.builder()
                .postId(post.getId())
                .build();
    }

    public static LikesToggleResponseDto toLikesToggleResponseDto(PostLike postLike, Integer likeCount) {
        return LikesToggleResponseDto.builder()
                .likesCount(likeCount)
                .likesStatus(postLike.getStatus().toString())
                .build();
    }
}
