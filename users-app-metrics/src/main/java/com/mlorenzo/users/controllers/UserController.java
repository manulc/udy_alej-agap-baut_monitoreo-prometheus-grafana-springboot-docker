package com.mlorenzo.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mlorenzo.users.entities.User;
import com.mlorenzo.users.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping
	public ResponseEntity<Page<User>> getUsers(
			@RequestParam(required = false, value = "page", defaultValue = "0") int page,
			@RequestParam(required = false, value = "size", defaultValue = "1000") int size) {
		return new ResponseEntity<>(service.getUsers(page, size), HttpStatus.OK);
	}

	@GetMapping("/usernames")
	public ResponseEntity<Page<String>> getUsernames(
			@RequestParam(required = false, value = "page", defaultValue = "0") int page,
			@RequestParam(required = false, value = "size", defaultValue = "1000") int size) {
		return new ResponseEntity<>(service.getUsernames(page, size), HttpStatus.OK);
	}

	@GetMapping("/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable("userId") Integer userId) {
		User user = service.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/username/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		return new ResponseEntity<>(service.getUserByUsername(username), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<User> authenticate(@RequestBody User user) {
		return new ResponseEntity<>(service.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()),
				HttpStatus.OK);
	}
	
	@DeleteMapping("/{username}")
	public ResponseEntity<Void> deleteUser(@PathVariable("username") String username){
		service.deleteUserByUsername(username);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
