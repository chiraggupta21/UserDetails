package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;

@FeignClient(name="user-service", fallback = UserFallback.class )
public interface ResourceService {

	   @GetMapping("/user")
	   public List<User> findAll();
	   @GetMapping("/user/getByUsername/{username}")
	   public User findByUsername(@PathVariable("username") String username);
	   
}
@Component
class UserFallback implements ResourceService 
{

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<User>();
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return new User();
	}
   
    
}
