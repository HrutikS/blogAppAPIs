package com.blog.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="post_id")
	private Integer postId;
	
	@Column(name="title", length = 100, nullable = false)
	private String postTitle;
	
	@Column(name="content")
	private String postContent;

}
