package com.example.community.dto.post.internal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
public class CommentDto {
    private Integer commentId;
    private String contents;
    private AuthorDto author;
    private LocalDateTime createdAt;
}
