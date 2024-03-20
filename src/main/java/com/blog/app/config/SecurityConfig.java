package com.blog.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.blog.app.security.JwtAuthenticationEntryPoint;
import com.blog.app.security.JwtAuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter; 
	
	//Spring Basic Authorization
	//This will help set authentication to JavaScript based instead of HTML/form based.
//	@Bean
//	protected SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception{
//		http
//			.csrf(csrf -> csrf.disable())
//			.authorizeHttpRequests((authz) -> authz
//					.anyRequest()
//					.authenticated())
//			.httpBasic(withDefaults());
//		return http.build();
//	}
	
	//Spring Jwt authorization
	//Complete the jwt authorization as per spring 3.x 
	@Bean
	protected SecurityFilterChain filterSecurity(HttpSecurity http) throws Exception{
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests((authz) -> authz
					.requestMatchers("/api/v1/auth/login").permitAll()
					.anyRequest().authenticated())
			.exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
			.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));	
			
		http
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
		
			
		
		return http.build();
	}

	//to get authentication manager
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {	  
       return authenticationConfiguration.getAuthenticationManager();
	}
	
	//to encode & decode password
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}