package com.example.community.dto.post;

import com.example.community.dto.post.internal.ContentDto;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostCreateRequestDto {
    private Long userId;
    private String title;
    private String text;
    private MultipartFile postImage;
}
