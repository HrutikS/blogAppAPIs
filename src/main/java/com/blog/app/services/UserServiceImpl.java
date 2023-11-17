package com.blog.app.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.app.entities.User;
import com.blog.app.payloads.UserDto;
import com.blog.app.repositories.UserRepository;
import com.blog.app.exceptions.ResourceNotFoundException;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	//Here, we are autowiring UserRepository, which is an interface. So how is that we are created an object of an interface.
	//The answer is jdk creates a proxy class for such cases at runtime. And the object created is object of that proxy class. 

	@Override
	public List<UserDto> getAllUsers() {
		List<User> listOfUsers = userRepository.findAll();
		List<UserDto> listOfUserDto = listOfUsers.stream().map( user -> this.toUserDto(user)).collect(Collectors.toList());
		return listOfUserDto;
	}
	
	@Override
	public UserDto getUserById(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.toUserDto(user);
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.toUser(userDto);
		User savedUser = userRepository.save(user);
		return this.toUserDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		user.setUserName(userDto.getUserName());
		user.setUserEmailId(userDto.getUserEmailId());
		user.setUserPassword(userDto.getUserPassword());
		user.setUserAbout(userDto.getUserAbout());
		User updatedUser = this.userRepository.save(user);
		return this.toUserDto(updatedUser);
	}
	
	@Override
	public void deleteUserById(Integer userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		userRepository.delete(user);
	}
	
	private User toUser(UserDto userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
//		user.setRoleId(userDto.getRoleId());
		user.setUserName(userDto.getUserName());
		user.setUserEmailId(userDto.getUserEmailId());
		user.setUserPassword(userDto.getUserPassword());
		user.setUserAbout(userDto.getUserAbout());
		return user;
	}
	
	private UserDto toUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
//		userDto.setRoleId(user.getRoleId());
		userDto.setUserName(user.getUserName());
		userDto.setUserEmailId(user.getUserEmailId());
		userDto.setUserPassword(user.getUserPassword());
		userDto.setUserAbout(user.getUserAbout());
		return userDto;
	}
	
	

}
