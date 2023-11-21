package com.blog.app.payloads;

import jakarta.validation.constraints.NotEmpty;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
	
	private int postId;
	
	@NotEmpty
	private String postTitle;
	
	@NotEmpty
	private String postContent;

	private String imageName;
//	Commented as we are taking it in service class.
	
	private Date postDate;
	
	private CategoryDto category;
	private UserDto user;
//	Here we are taking DTOs, as if we take just user and category, 
//	the requests will go under infinite recursion since category & user both contain post internally.
}
