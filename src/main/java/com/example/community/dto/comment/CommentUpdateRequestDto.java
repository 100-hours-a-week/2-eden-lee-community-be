package com.example.community.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class CommentUpdateRequestDto {
    private Long userId;
    private Long postId;
    private String contents;
}
