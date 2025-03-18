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
    private Integer postId;
    private String title;
    private ContentDto content;
    private AuthorDto author;
    private CountDto counts;
    private String likeYn;
    private List<CommentDto> comments;
    private LocalDateTime createdAt;
}
