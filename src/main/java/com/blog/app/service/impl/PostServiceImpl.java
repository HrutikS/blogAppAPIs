package com.blog.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.app.entities.Category;
import com.blog.app.entities.Post;
import com.blog.app.entities.User;
import com.blog.app.exceptions.ResourceNotFoundException;
import com.blog.app.payloads.PostDto;
import com.blog.app.payloads.PostResponse;
import com.blog.app.repositories.CategoryRepository;
import com.blog.app.repositories.PostRepository;
import com.blog.app.repositories.UserRepository;
import com.blog.app.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	private Integer defaultPageSize = 5;

	
	//to create a new post
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

		Post post = modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setPostDate(new Date());
		post.setCategory(category);
		post.setUser(user);

		Post createdPost = postRepository.save(post);
		return modelMapper.map(createdPost, PostDto.class);
	}

	
	//to update an existing post
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		post.setPostTitle(postDto.getPostTitle());
		post.setPostContent(postDto.getPostContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = postRepository.save(post);
		return modelMapper.map(updatedPost, PostDto.class);
	}

	
	//to delete a post
	@Override
	public void deletePost(Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		postRepository.delete(post);
	}

	
	//to get all posts
	@Override
	public PostResponse getAllPosts(Integer pageNumber, String sortBy, String sortDirection) {

		// Pagination & sorting implementation
		Pageable pageable = setPaginationAndSorting(pageNumber, sortBy, sortDirection);
		Page<Post> pageOfPosts = postRepository.findAll(pageable);
		List<Post> listOfPosts = pageOfPosts.getContent();

		List<PostDto> listOfPostDtos = listOfPosts.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		// Custom response for posts containing pages and posts details
		PostResponse postResponse = new PostResponse();
		postResponse = setPostResponse(postResponse, listOfPostDtos, pageOfPosts);

		return postResponse;
	}

	
	//to get post by postId
	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
		PostDto postDto = modelMapper.map(post, PostDto.class);
		return postDto;
	}

	
	//to get posts by specific category	
	@Override
	public PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, String sortBy, String sortDirection) {
		Category category = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

		// Pagination & sorting implementation
		Pageable pageable = setPaginationAndSorting(pageNumber, sortBy, sortDirection);
		Page<Post> pageOfPosts = postRepository.findByCategory(category, pageable);
		List<Post> listOfPosts = pageOfPosts.getContent();

		List<PostDto> listOfPostDtos = listOfPosts.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		// Custom response for posts containing pages and posts details
		PostResponse postResponse = new PostResponse();
		postResponse = setPostResponse(postResponse, listOfPostDtos, pageOfPosts);
		
		return postResponse;
	}

	
	//to get posts by specific user
	@Override
	public PostResponse getPostsByUser(Integer userId, Integer pageNumber, String sortBy, String sortDirection) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		
		// Pagination & sorting implementation
		Pageable pageable = setPaginationAndSorting(pageNumber, sortBy, sortDirection);
		Page<Post> pageOfPosts = postRepository.findByUser(user, pageable);
		List<Post> listOfPosts = pageOfPosts.getContent();

		List<PostDto> listOfPostDtos = listOfPosts.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		// Custom response for posts containing pages and posts details
		PostResponse postResponse = new PostResponse();
		postResponse = setPostResponse(postResponse, listOfPostDtos, pageOfPosts);
		
		return postResponse;
	}

	
	//To get posts containing specific keyword
	@Override
	public PostResponse getPostsBySearch(String keyword, Integer pageNumber, String sortBy, String sortDirection) {

		List<Post> listOfPosts = postRepository.findByPostTitleContaining(keyword);

		// For no posts found with matching keyword
		if (listOfPosts.isEmpty()) {
			// Custom response for no posts
			PostResponse postResponse = new PostResponse();
			postResponse.noPostsForKeyword("Posts", "keyword", keyword);
		}

		// For posts found with matching keyword
		Pageable pageable = setPaginationAndSorting(pageNumber, sortBy, sortDirection);
		Page<Post> pageOfPosts = postRepository.findByPostTitleContaining(keyword, pageable);
		listOfPosts = pageOfPosts.getContent();

		List<PostDto> listOfPostDtos = listOfPosts.stream().map((post) -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());

		// Custom response for posts containing pages and posts details
		PostResponse postResponse = new PostResponse();
		postResponse = setPostResponse(postResponse, listOfPostDtos, pageOfPosts);

		return postResponse;

	}
	
	
	// set pagination & sorting
	public Pageable setPaginationAndSorting(Integer pageNumber, String sortBy, String sortDirection) {
		// Sorting posts
		Sort sort = (sortDirection.equalsIgnoreCase("asc")) ? (Sort.by(sortBy).ascending()) : (Sort.by(sortBy).descending());
		// Pagination implementation
		Pageable pageable = PageRequest.of(pageNumber, defaultPageSize, sort);
		return pageable;
	}
	

	// To set post response
	public PostResponse setPostResponse(PostResponse postResponse, List<PostDto> listOfPostDtos, Page<Post> pageOfPosts) {
		// Setting post response
		postResponse.setPosts(listOfPostDtos);
		postResponse.setPageNumber(pageOfPosts.getNumber());
		postResponse.setPageSize(defaultPageSize);
		postResponse.setTotalPosts(pageOfPosts.getTotalElements());
		postResponse.setTotalPages(pageOfPosts.getTotalPages());
		postResponse.setLastPage(pageOfPosts.isLast());
		return postResponse;
	}

}
