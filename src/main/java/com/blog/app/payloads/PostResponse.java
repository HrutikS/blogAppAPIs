package com.blog.app.payloads;

import java.util.List;

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

}
