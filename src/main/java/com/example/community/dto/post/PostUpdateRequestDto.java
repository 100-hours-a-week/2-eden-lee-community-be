package com.example.community.dto.post;

import com.example.community.dto.post.internal.ContentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PostUpdateRequestDto {
    private Integer userId;
    private String title;
    private ContentDto content;
}

