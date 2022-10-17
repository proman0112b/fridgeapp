package com.bd.post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bd.post.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(Integer id);
    Optional<User> findByEmailAndPassword(String email, String password);

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

    Page<User> findByRole(String role, Pageable pageable);

}