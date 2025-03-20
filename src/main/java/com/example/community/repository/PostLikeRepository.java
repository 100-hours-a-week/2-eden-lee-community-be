package com.example.community.repository;

import com.example.community.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
}
