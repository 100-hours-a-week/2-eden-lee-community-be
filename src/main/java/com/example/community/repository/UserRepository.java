package com.example.community.repository;

import com.example.community.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);

    Optional<User> findByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM User u WHERE u.id = :id AND u.deletedAt IS NULL")
    Optional<User> findActiveUserById(@Param("id") Long id);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.deletedAt IS NULL")
    Optional<User> findActiveUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.nickname = :nickname AND u.deletedAt IS NULL")
    Optional<User> findActiveUserByNickname(@Param("nickname") String nickname);
}
