package com.blog.app.payloads;

import java.util.List;

import com.blog.app.exceptions.ResourceNotFoundException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostResponse {
	
	private List<PostDto> posts;
	
	private Integer pageNumber;
	
	private Integer pageSize;
	
	private long totalPosts;
	
	private Integer totalPages;
	
	private boolean lastPage;
	
	public void noPostsForKeyword(String resourceName, String fieldName, String fieldValue) {
		throw new ResourceNotFoundException(resourceName, fieldName, fieldValue);
	}
}
