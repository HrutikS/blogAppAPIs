package com.blog.app.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entities.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	Optional<User> findByUserEmailId(String userEmailId);
}
