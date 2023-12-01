package com.blog.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args){
		SpringApplication.run(BlogAppApplication.class, args);
	}
	
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	//We have already saved passwords in database, so to get the encoded value of 'password'
		//and then we'll store this encoded value into database manually. 
	//All this is to check if passwordEncoder is working.
	@Override
	public void run(String... args) throws Exception {
		System.out.println(passwordEncoder.encode("password"));
	}
}