package com.bolgapplication.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bolgapplication.entities.Category;
import com.bolgapplication.entities.Comment;
import com.bolgapplication.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PostDto {
	private Integer postId;
	@NotBlank
	@Size(min=5)
	private String postTitle;
	@NotBlank
	@Size(min=5)
	private String content;
	@NotBlank
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDTO user;
	private Set<CommentDto> comments = new HashSet<>();

}
