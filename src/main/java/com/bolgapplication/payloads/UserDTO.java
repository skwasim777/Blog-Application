package com.bolgapplication.payloads;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDTO {
	private Integer id;
	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 character")
	private String name;
	@Email(message = "Email address is not valid")
	private String email;
	@NotEmpty
	@Size(min = 3, max = 10, message = "Password must be min of 3 char or max of 10 char")
	private String password;
	@NotEmpty
	private String about;
	private Set<CommentDto> comments = new HashSet<>();
}
