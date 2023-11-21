package com.blog.app.services;

import java.util.List;

import com.blog.app.payloads.PostDto;
import com.blog.app.payloads.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPosts(Integer pageNumber, String sortBy, String sortDirection);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	List<PostDto> getPostsByUser(Integer userId);
	
//	List<PostDto> getPostBySearch(String keyword);

}
