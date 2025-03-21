package com.example.community.dto.post.internal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
public class PostDto {
    private Long postId;
    private String title;
    private ContentDto contents;
    private AuthorDto author;
    private CountDto counts;
    private String likeStatus;
    private List<CommentDto> comments;
    private LocalDateTime createdAt;
}
