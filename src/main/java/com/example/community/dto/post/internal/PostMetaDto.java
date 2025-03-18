package com.example.community.dto.post.internal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class PostMetaDto {
    private Integer postId;
    private String title;
    private AuthorDto author;
    private CountDto counts;
    private LocalDateTime createdAt;
}
