package com.blog.app.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	
	@NotEmpty
	@Size(min = 3, message = "Username must contain atleast three characters !!")
	private String userName;
	
	@Email()
	@Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}" , message = "Email is invalid !!")
	private String userEmailId;
	
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must contain minimum three and maximum ten characters !!")
	private String userPassword;
	
	@NotEmpty(message = "About must not be empty !!")
	private String userAbout;

}
