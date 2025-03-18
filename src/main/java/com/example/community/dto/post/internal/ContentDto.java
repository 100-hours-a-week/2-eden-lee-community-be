package com.example.community.dto.post.internal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ContentDto {
    private String text;
    private String imageUrl;
}
