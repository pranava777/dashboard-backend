package com.dashboard.api.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dashboard.api.model.Employee;
import com.dashboard.api.model.User;
import com.dashboard.api.dao.EmployeeRepository;
import com.dashboard.api.service.EmployeeService;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping(path = "/")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;  //reference variable of EmployeeService which will bind at runtime (Loosely coupled code)

	//method for getting all the employees
	@GetMapping(path="/allEmployee") 
	public @ResponseBody List<Employee> getAllUsers() {
		// This returns a List of employees
		Iterable<Employee> l= employeeService.getUsers();
		List<Employee> list = new ArrayList<Employee>();
	    
		l.forEach(list::add);
		
		return list;
	}
	
	//method for saving an employees or update (JPA handles for update if id is present in db for employee
	@PostMapping(path="/employeeSave", consumes = {"application/json"}) // Map ONLY POST Requests public @ResponseBody
	public ObjectNode addNewUser (@RequestBody Employee employee) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode node = new ObjectNode(factory);
		
		String response = null;
		try {
			  response = employeeService.addUser(employee);	
			
			  if(response.equals("Saved")) 
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
			  System.out.println("Internal Server Error." + e);
			  node.put("message","Internal Server Error." + e); 
		 }
			
			
		
		return node;
	}
	 
	
	//method for deleting an employee
	@PostMapping(path="/deleteEmployee", consumes = {"application/json"}) // Map ONLY POST Requests public @ResponseBody
	public ObjectNode validateUser (@RequestBody Employee employee) {
		
		JsonNodeFactory factory = JsonNodeFactory.instance;
		ObjectNode node = new ObjectNode(factory);
		
		Integer employeeId = null;
		boolean validateUser = false;
		List<Employee> result = new ArrayList<Employee>();
	    
		Iterable<Employee> l = getAllUsers();
		l.forEach(result::add);
		
		
		  try { 
			  for (Employee u : result) {
				  if(u.getFirstName().contentEquals(employee.getFirstName()) && u.getLastName().contentEquals(employee.getLastName() )) {
					  employeeId = (int) u.getId();
					  validateUser = true; 
				  } else {
		  
				  } 
			  }
		  
			  if(validateUser) 
			  {
				  employeeService.deleteUser(employeeId); 
				  node.put("message","Success"); 
			  }
			  else
			  { 
				  node.put("message","Failure"); 
			  } 
		  	}
		  
		  catch (Exception e) 
		  {
			  System.out.println("Internal Server Error." + e);
		  }
		
		return node;
	}

}
