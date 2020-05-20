package com.dashboard.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dashboard.api.dao.UserRepository;
import com.dashboard.api.model.User;

//service for implementing the methods of crudrepository
@Component
public class UserService {

	//reference variable of UserRepository which will bind at runtime (Loosely coupled code)
	@Autowired
	private UserRepository userRepository;
	
	public Iterable<User> getUsers() {
		
		return userRepository.findAll();
	}
	
	public String addUser(User user) {
		
		userRepository.save(user);
		return "Saved";
	}
	
}
