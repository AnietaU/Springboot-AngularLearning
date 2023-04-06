package com.backend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.entities.Employee;
import com.backend.exception.ResourceNotFoundException;
import com.backend.repository.EmployeeRepo;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	EmployeeRepo repo;
	
//	getAll Employees R
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return repo.findAll();
	}
	
//	create C
	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee emp) {
		return this.repo.save(emp);
		
	}
//	getById R
	@GetMapping("/employees/{id}")
	public ResponseEntity< Employee> getEmployeeById(@PathVariable("id") int id) {
		 Employee employee= this.repo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee does not exist with id: " + id));
		 return ResponseEntity.ok(employee);	
	}
	
//	Update U
	@PutMapping("/employees/update/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,  @PathVariable("id") int id) {
		Employee emp= this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Employee does not exist with id: " + id));
		emp.setName(employee.getName());
		emp.setDesignation(employee.getDesignation());
		emp.setEmail(employee.getEmail());
		Employee updated =this.repo.save(emp);	
		return ResponseEntity.ok(updated);
		
		}
	
//	delete D
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable("id") int id){
		Employee emp = this.repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee does not exist"));
		
		this.repo.delete(emp);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return ResponseEntity.ok(response);
	}
	
	

}
