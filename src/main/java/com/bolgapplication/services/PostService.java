package com.bolgapplication.services;

import java.util.List;

import com.bolgapplication.entities.Post;
import com.bolgapplication.payloads.PostDto;
import com.bolgapplication.payloads.PostResponse;

public interface PostService {
	// create post
	PostDto createPost(PostDto postDto,Integer userId,Integer catId);

	// update post by id
	PostDto updatePost(PostDto postDto, Integer postId);

	// delete post by id
	void deletePost(Integer postId);

	// get all posts
	PostResponse getAllPosts(Integer pageNumber,Integer pageSize,String ssortBy,String sortDir);

	// get a single post by id
	PostDto getPostById(Integer postId);

	// get post by categories
	List<PostDto> getPostByCategories(Integer catId);

	// get post by users
	List<PostDto> getPostByUsers(Integer userId);

	// search post by key
	List<PostDto> searchPosts(String keyword);
}
