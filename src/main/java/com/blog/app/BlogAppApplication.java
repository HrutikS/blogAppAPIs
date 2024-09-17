package com.blog.app;

import com.blog.app.config.AppConstants;
import com.blog.app.entities.Role;
import com.blog.app.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApplication implements CommandLineRunner{
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

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
		System.out.println("Encoded 'password' : "+passwordEncoder.encode("password"));

		try {

			Role adminUserRole = new Role();
			adminUserRole.setRoleId(AppConstants.ADMIN_USER);
			adminUserRole.setRoleName("ADMIN_USER");

			Role normalUserRole = new Role();
			normalUserRole.setRoleId(AppConstants.NORMAL_USER);
			normalUserRole.setRoleName("NORMAL_USER");

			List<Role> roles = List.of(adminUserRole, normalUserRole);

			List<Role> rolesResult = roleRepository.saveAll(roles);

			rolesResult.forEach(r -> {
				System.out.println("CREATED ROLE : "+r.getRoleName());
			});

		} catch (Exception e){
			//TODO : Handle Exception
			e.printStackTrace();
		}
	}
}