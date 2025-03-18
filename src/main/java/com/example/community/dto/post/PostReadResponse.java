package com.example.community.dto.post;

import com.example.community.dto.post.internal.PostDto;
import com.example.community.dto.post.internal.PostMetaDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PostReadResponse {
    private PostDto post;
}
