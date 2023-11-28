package com.blog.app.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto {

	private Integer commentId;
	
	private String commentContent;
	
	private UserDto user;
	
}
