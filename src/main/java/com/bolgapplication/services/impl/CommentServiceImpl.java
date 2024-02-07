package com.bolgapplication.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bolgapplication.entities.Comment;
import com.bolgapplication.entities.Post;
import com.bolgapplication.entities.User;
import com.bolgapplication.exceptions.ResourceNotFoundException;
import com.bolgapplication.payloads.CommentDto;
import com.bolgapplication.repositories.CommentRepo;
import com.bolgapplication.repositories.PostRepo;
import com.bolgapplication.repositories.UserRepo;
import com.bolgapplication.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private PostRepo postRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private ModelMapper mapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post id", postId));
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","user id", userId));
		Comment comment = this.mapper.map(commentDto,Comment.class);
		comment.setPost(post);
		comment.setUser(user);
		Comment savedComment = this.commentRepo.save(comment);
		return this.mapper.map(savedComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer comm_id) {
		Comment com = this.commentRepo.findById(comm_id).orElseThrow(()->new ResourceNotFoundException("Comment","comment id", comm_id));
		this.commentRepo.delete(com);
	}

}
