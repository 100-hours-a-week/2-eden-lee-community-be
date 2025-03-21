package com.example.community.dto.post.internal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class AuthorDto {
    private Long userId;
    private String nickname;
    private String profileImageUrl;
}
