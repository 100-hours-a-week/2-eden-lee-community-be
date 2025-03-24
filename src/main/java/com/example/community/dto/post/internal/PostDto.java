package com.example.community.dto.post.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private String likesStatus;
    private List<CommentDto> comments;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
}
