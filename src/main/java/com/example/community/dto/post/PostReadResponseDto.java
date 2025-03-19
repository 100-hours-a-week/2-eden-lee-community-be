package com.example.community.dto.post;

import com.example.community.dto.post.internal.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PostReadResponseDto {
    private PostDto post;
}
