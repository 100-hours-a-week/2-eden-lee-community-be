package com.example.community.dto.post.internal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CountDto {
    private int likes;
    private int comments;
    private int views;
}
