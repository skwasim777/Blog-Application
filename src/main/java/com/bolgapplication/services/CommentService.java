package com.bolgapplication.services;

import com.bolgapplication.payloads.CommentDto;

public interface CommentService {
	CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId);

	void deleteComment(Integer comm_id);
}
