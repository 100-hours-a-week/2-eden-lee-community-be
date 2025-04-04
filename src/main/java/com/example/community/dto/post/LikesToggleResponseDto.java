package com.example.community.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class LikesToggleResponseDto {
    private Integer likesCount;
    private String likesStatus;
}
