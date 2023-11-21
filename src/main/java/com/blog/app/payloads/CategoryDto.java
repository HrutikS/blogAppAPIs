package com.blog.app.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
	
	private int categoryId;
	
	@NotEmpty
	@Size(min = 4, message = "Title must contain minimum four characters")
	private String categoryTitle;
	
	@NotEmpty
	@Size(min = 10, message = "Description length should be minimum of ten characters")
	private String categoryDescription;

}
