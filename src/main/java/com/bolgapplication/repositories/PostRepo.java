package com.bolgapplication.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolgapplication.entities.Category;
import com.bolgapplication.entities.Post;
import com.bolgapplication.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post> findByUser(User user);

	List<Post> findByCategory(Category category);
	
	  List<Post> findByPostTitleContaining(String postTitle); // Corrected method name
}
