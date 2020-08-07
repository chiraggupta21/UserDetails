package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.ResourceService;

@RestController
public class ResourceController {

	@Autowired
	ResourceService resourceService;
	
	@GetMapping("/getAllUser")
	List<User> gettingdata() {
		return resourceService.findAll();
	}	
	
	@GetMapping("/getByusername/{username}")
	User getByUsername(@PathVariable String username) {
		return resourceService.findByUsername(username);
	}
}
