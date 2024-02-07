package com.bolgapplication.payloads;

import com.bolgapplication.entities.Post;
import com.bolgapplication.entities.User;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class CommentDto {
	private Integer comm_id;
	private String comm_content;
}
