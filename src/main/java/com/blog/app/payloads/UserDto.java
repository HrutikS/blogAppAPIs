package com.blog.app.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	private int userId;
	
//	private int roleId;
	
	private String userName;
	private String userEmailId;
	private String userPassword;
	private String userAbout;

}
