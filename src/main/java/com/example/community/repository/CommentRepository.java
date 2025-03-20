package com.example.community.repository;

import com.example.community.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
