package com.bolgapplication.services;

import java.util.List;

import com.bolgapplication.payloads.UserDTO;

public interface UserService {
	UserDTO registerNewUser(UserDTO userDto);
	UserDTO crateUser(UserDTO user);
	UserDTO updateUSer(UserDTO user,Integer id);
	UserDTO getUserById(Integer id);
	List<UserDTO> getAllUsers();
	void deleteUserById(Integer id);
	void deleteAllUsers();
}
