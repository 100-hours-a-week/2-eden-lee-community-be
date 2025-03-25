package com.example.community.dto.post;

import com.example.community.dto.post.internal.ContentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PostCreateRequestDto {
    private Long userId;
    private String title;
    private String text;
    private MultipartFile postImage;
}
