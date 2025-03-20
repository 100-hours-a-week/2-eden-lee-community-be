package com.example.community.repository;

import com.example.community.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    Optional<PostLike> findByPostId(Long postId);

    Optional<PostLike> findByPostIdAndUserId(Long postId, Long userId);
}
