package com.blog.app.services;

import java.util.List;

import com.blog.app.payloads.UserDto;

public interface UserService {

	UserDto registerUser(UserDto userDto);
	
	List<UserDto> getAllUsers();
	
	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	void deleteUserById(Integer userId);

}
