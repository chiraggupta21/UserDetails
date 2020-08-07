package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserRepository rep;
	
	@GetMapping
	List<User> getEmployee(){
		return rep.findAll();
	}
	@GetMapping("getById/{id}")
	User getUserByid(@PathVariable int id){
		return rep.findById(id).get();
	}
	@DeleteMapping("deleteId/{id}")
	User deleteUser(@PathVariable int id)
	{
		User e=rep.findById(id).get();
		 rep.deleteById(id);
		 return e;
	}
	@PostMapping
	ResponseEntity<User> addUser(@RequestBody User user)
	{	user.setPassword(passwordEncoder().encode(user.getPassword()));
		User returnuser=rep.save(user);
		return new ResponseEntity<User>(returnuser, HttpStatus.OK);
	}
	@PutMapping
	void updateUser(@RequestBody User user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		rep.save(user);
	}	
	@GetMapping("getByUsername/{username}")
	User getByUserName(@PathVariable String username){
		return rep.findByUsername(username);
	}
	
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
}
