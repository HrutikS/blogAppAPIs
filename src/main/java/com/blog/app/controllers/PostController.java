package com.blog.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.app.payloads.ApiResponse;
import com.blog.app.payloads.PostDto;
import com.blog.app.services.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/blog/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//Create Post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto, 
							@PathVariable Integer userId, 
							@PathVariable Integer categoryId){
		
		PostDto createdPost = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
	
	}
	
	//Delete Post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post Deleted Successfully !!", true), HttpStatus.OK);
	}
	
	//Get All Post
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts(){
		List<PostDto> listOfPostDtos = postService.getAllPosts();
		return new ResponseEntity<List<PostDto>>(listOfPostDtos,HttpStatus.OK);
	}
	
	//Get Post by PostId
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto fetchedPost = postService.getPostById(postId);
		return new ResponseEntity<PostDto>(fetchedPost, HttpStatus.OK);
	}
	
	//Get Posts by Category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
		List<PostDto> listOfFetchedPost = postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(listOfFetchedPost, HttpStatus.OK);
	}
	
	//Get Posts by User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
		List<PostDto> listOfFetchedPost = postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(listOfFetchedPost, HttpStatus.OK);
	}
	
}
