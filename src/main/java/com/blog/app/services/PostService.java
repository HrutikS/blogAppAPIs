package com.blog.app.services;

import com.blog.app.payloads.PostDto;
import com.blog.app.payloads.PostResponse;

public interface PostService {
	
	//to create a new post
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//to update existing post
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//to delete a post 
	void deletePost(Integer postId);

	//to get all posts
	PostResponse getAllPosts(Integer pageNumber, String sortBy, String sortDirection);
	
	//to get post by postId
	PostDto getPostById(Integer postId);
	
	//to get posts by specific category	
	PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, String sortBy, String sortDirection);
	
	//to get posts by specific user
	PostResponse getPostsByUser(Integer userId, Integer pageNumber, String sortBy, String sortDirection);
	
	//To get posts containing specific keyword
	PostResponse getPostsBySearch(String keyword, Integer pageNumber, String sortBy, String sortDirection);
}
