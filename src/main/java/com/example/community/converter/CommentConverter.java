package com.example.community.converter;

import com.example.community.domain.Comment;
import com.example.community.dto.comment.CommentCreateResponseDto;
import com.example.community.dto.comment.CommentUpdateResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentConverter {

    public static CommentCreateResponseDto toCommentCreateResponseDto(Comment comment) {
        return CommentCreateResponseDto.builder()
                .commentId(comment.getId())
                .build();
    }

    public static CommentUpdateResponseDto toCommentUpdateResponseDto(Comment comment) {
        return CommentUpdateResponseDto.builder()
                .commentId(comment.getId())
                .build();
    }
}
