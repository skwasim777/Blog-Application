package com.bolgapplication.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolgapplication.payloads.ApiResponse;
import com.bolgapplication.payloads.UserDTO;
import com.bolgapplication.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userdto) {
		UserDTO createUserdto = this.userService.crateUser(userdto);
		return new ResponseEntity(createUserdto, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userdto, @PathVariable Integer id) {
		UserDTO updatedUSer = this.userService.updateUSer(userdto, id);
		return new ResponseEntity(updatedUSer, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer id) {
		this.userService.deleteUserById(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully ", true), HttpStatus.OK);

	}

	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getSingleUser(@PathVariable Integer id) {
		return ResponseEntity.ok(this.userService.getUserById(id));

	}
}
