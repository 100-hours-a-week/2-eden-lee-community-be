package com.example.community.domain;

import com.example.community.domain.common.BaseEntity;
import com.example.community.domain.enums.PostLikeStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "post_likes")
public class PostLike extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PostLikeStatus status;

    public void activate() {
        this.status = PostLikeStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = PostLikeStatus.INACTIVE;
    }

    public PostLikeStatus getStatus() {
        return status;
    }
}
