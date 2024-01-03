package com.bolgapplication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolgapplication.entities.User;

public interface UserRepo extends JpaRepository<User,Integer>{
	
}
