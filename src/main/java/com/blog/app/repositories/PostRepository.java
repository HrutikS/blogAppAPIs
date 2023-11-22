package com.blog.app.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;

public interface PostRepository extends JpaRepository<Post, Integer>{
	
	Page<Post> findByUser(User user, Pageable pageable);
	
	Page<Post> findByCategory(Category category, Pageable pageable);
	
	List<Post> findByPostTitleContaining(String keyword);
	
	Page<Post> findByPostTitleContaining(String keyword, Pageable pageable); //Added Pageable to introduce pagination.
}
