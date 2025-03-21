package com.example.community.service;

import com.example.community.apiPayload.code.status.ErrorStatus;
import com.example.community.apiPayload.exception.handler.TempHandler;
import com.example.community.converter.CommentConverter;
import com.example.community.domain.Comment;
import com.example.community.domain.Post;
import com.example.community.domain.User;
import com.example.community.dto.comment.CommentCreateResponseDto;
import com.example.community.dto.comment.CommentUpdateResponseDto;
import com.example.community.repository.CommentRepository;
import com.example.community.repository.PostRepository;
import com.example.community.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public CommentCreateResponseDto createComment(Long userId,
                                                  Long postId,
                                                  String contents) {
        User user = userRepository.findActiveUserById(userId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.USER_NOT_FOUND));

        Post post = postRepository.findActivePostById(postId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.POST_NOT_FOUND));

        Comment comment = Comment.builder()
                .user(user)
                .post(post)
                .contents(contents)
                .build();
        commentRepository.save(comment);

        return CommentConverter.toCommentCreateResponseDto(comment);
    }

    @Transactional
    public CommentUpdateResponseDto updateComment(Long commentId, String contents) {
        Comment comment = commentRepository.findActiveCommentById(commentId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.COMMENT_NOT_FOUND));
        comment.updateContents(contents);

        return CommentConverter.toCommentUpdateResponseDto(comment);
    }

    @Transactional
    public Void deleteComment(Long commentId) {
        Comment comment = commentRepository.findActiveCommentById(commentId)
                .orElseThrow(() -> new TempHandler(ErrorStatus.COMMENT_NOT_FOUND));
        comment.delete();

        return null;
    }
}
