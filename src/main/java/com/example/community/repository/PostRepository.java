package com.example.community.repository;

import com.example.community.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
