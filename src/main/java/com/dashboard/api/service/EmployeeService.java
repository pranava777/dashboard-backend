package com.dashboard.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dashboard.api.dao.EmployeeRepository;
import com.dashboard.api.model.Employee;

@Component
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Iterable<Employee> getUsers() {
		// This returns a JSON or XML with the users 
		return employeeRepository.findAll();
	}
	
	public String addUser(Employee employee) {
		
		employeeRepository.save(employee);
		return "Saved";
	}
	
	public String deleteUser(Integer employeeId) {
		
		Long l= new Long(employeeId);
		employeeRepository.deleteById(l);
		return "Deleted";
	}
	
	public Optional<Employee> findEmployeeById(Long employeeId) {
		// This returns a JSON or XML with the users 
		return employeeRepository.findById(employeeId);
	}
	
	
}
