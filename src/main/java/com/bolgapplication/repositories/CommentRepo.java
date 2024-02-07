package com.bolgapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolgapplication.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer>{

}
