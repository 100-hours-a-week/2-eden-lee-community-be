package com.example.community.domain;

import com.example.community.domain.common.BaseEntity;
import com.example.community.domain.enums.PostLikeStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "posts")
public class Post extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer views;

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> commentList  = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostLike> postLikes = new ArrayList<>();

    public Integer getCommentCount() {
        return (int) commentList.stream()
                .filter(comment -> comment.getDeletedAt() == null)
                .count();
    }

    public Integer getLikeCount() {
        return (int) postLikes.stream()
                .filter(like -> like.getStatus() == PostLikeStatus.ACTIVE)
                .count();
    }

    public PostLikeStatus getUserLikeStatus(Long userId) {
        return postLikes.stream()
                .filter(like -> like.getUser().getId().equals(userId))
                .map(PostLike::getStatus)
                .findFirst()
                .orElse(PostLikeStatus.INACTIVE);
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updateContents(String text, String imageUrl) {
        this.text = text;

        if (imageUrl != null) {
            this.imageUrl = imageUrl;
        }
    }

    public void increaseViews() {
        this.views++;
    }
}
