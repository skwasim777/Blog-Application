package com.bolgapplication.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bolgapplication.config.AppConstant;
import com.bolgapplication.entities.Role;
import com.bolgapplication.entities.User;
import com.bolgapplication.exceptions.ResourceNotFoundException;
import com.bolgapplication.payloads.UserDTO;
import com.bolgapplication.repositories.RoleRepo;
import com.bolgapplication.repositories.UserRepo;
import com.bolgapplication.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private RoleRepo roleRepo;

	public UserDTO crateUser(UserDTO userdto) {
		User user = this.dtoToUser(userdto);
		User savedUsers = this.userRepo.save(user);
		return this.userToUserDTO(savedUsers);
	}

	@Override
	public UserDTO updateUSer(UserDTO userdto, Integer id) {
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", " id ", id));
		user.setName(userdto.getName());
		user.setEmail(userdto.getEmail());
		user.setPassword(userdto.getPassword());
		user.setAbout(userdto.getAbout());

		User updatedUser = this.userRepo.save(user);
		UserDTO userdto1 = this.userToUserDTO(updatedUser);
		return userdto1;
	}

	@Override
	public UserDTO getUserById(Integer id) {
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return this.userToUserDTO(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDTO> userdtos = users.stream().map(user -> this.userToUserDTO(user)).collect(Collectors.toList());
		return userdtos;
	}

	@Override
	public void deleteUserById(Integer id) {
		User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		this.userRepo.delete(user);
	}

	private User dtoToUser(UserDTO userdto) {
		User user = this.modelMapper.map(userdto, User.class);
		return user;
	}

	private UserDTO userToUserDTO(User user) {
		UserDTO userdto = this.modelMapper.map(user, UserDTO.class);
		return userdto;

	}

	@Override
	public void deleteAllUsers() {
		this.userRepo.deleteAll();
	}

	@Override
	public UserDTO registerNewUser(UserDTO userDto) {
		userDto.setId(null);
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Role role = this.roleRepo.findById(AppConstant.NORMAL_USER).get();
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDTO.class);
	}

}
