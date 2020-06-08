package com.dashboard.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.model.User;
import com.dashboard.api.dao.UserRepository;
import com.dashboard.api.service.UserService;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(path = "/")
public class ManagerController {
	
	//reference variable of UserService
	@Autowired
	private UserService userService; //reference variable of UserService which will bind at runtime (Loosely coupled code)

	//to get all the managers data
	@GetMapping(path="/all") 
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users 
		return userService.getUsers();
	}
	
	//method for signup returning ObjectNode instance
	@PostMapping(path="/account/register", consumes = {"application/json"}) // Map ONLY POST Requests public @ResponseBody
	public ObjectNode addNewUser (@RequestBody User user) {
		
		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode node = new ObjectNode(factory);
		
		boolean userExists = false;
		List<User> result = new ArrayList<User>();
	    
		Iterable<User> l = getAllUsers();
		l.forEach(result::add);
		
		
		  try { 
			  for (User u : result) {
				  if(u.getUsername().contentEquals(user.getUsername())) { 
					  userExists = true; 
				  } else {
		  
				  } 
			  }
		  
			  if(userExists) 
			  { 
				  node.put("message","Failure"); 
			  } 
			  else 
			  {
				  userService.addUser(user); 
				  node.put("message","Success"); }
		  	}
		  
		  catch (Exception e) 
		  {
			  System.out.println("Internal Server Error.");
		  }
		 
		
		return node;
	}
	 
	 
	//method for login returning ObjectNode instance
	@PostMapping(path="/account/login", consumes = {"application/json"}) // Map ONLY POST Requests public @ResponseBody
	public ObjectNode validateUser (@RequestBody User user) {
		
		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode node = new ObjectNode(factory);
		
		boolean isValid = false;
		List<User> result = new ArrayList<User>();
	    
		Iterable<User> l = getAllUsers();
		l.forEach(result::add);
		
		
		try { 
			  for (User u : result) 
			  {
				  if(u.getUsername().contentEquals(user.getUsername()) && u.getPassword().contentEquals(user.getPassword())) 
				  { 
					  isValid = true; 
				  } else {
		  
				  }
			  }
		  
			  if(isValid) 
			  { 
				  node.put("message","Success"); 
			  } 
			  else 
			  {
				  node.put("message","Failure"); 
			  }
		  
		  }
		  
		  catch (Exception e) 
		  { 
			  System.out.println("Internal Server Error.");
		  }
		 
		
		return node;
	}

}
