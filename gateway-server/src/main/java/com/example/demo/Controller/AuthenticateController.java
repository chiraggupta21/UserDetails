package com.example.demo.Controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AuthenticateController {

	@GetMapping("/authenticate")
	public Map<String, String> AuthenticateUser(@RequestHeader("Authorization") String authHeader){
		String username=getUser(authHeader);
		String role=SecurityContextHolder.getContext().getAuthentication().getAuthorities().toArray()[0].toString();
		String token=generateToken(username,role);
		Map<String,String> sendingdata=new HashMap<>();
		sendingdata.put("username", username);
		sendingdata.put("role", role);
		sendingdata.put("token", token);
		return sendingdata;
		
	}
	
	String getUser(String authheader) {
		return (new String(Base64.getDecoder().decode(authheader.split(" ")[1]))).split(":")[0];
	}
	String generateToken(String username,String role) {
		JwtBuilder jwtBuilder=Jwts.builder();
		jwtBuilder.setSubject(username)
				  .claim("role", role)
				  .setIssuedAt(new Date())
				  .setExpiration(new Date(new Date().getTime()+1200000))
				  .signWith(SignatureAlgorithm.HS256, "secretkey");
	
		return jwtBuilder.compact();
	}
}
