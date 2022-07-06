package com.sicpa.technical.interview.proposal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicpa.technical.interview.proposal.dto.EmployeesDto;
import com.sicpa.technical.interview.proposal.exceptions.ResourceException;
import com.sicpa.technical.interview.proposal.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeesController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/listEmployee")
	public ResponseEntity<List> getAllEnterprises() {
		try {
			return new ResponseEntity(employeeService.getAllEmployees(), HttpStatus.OK);
		} catch (ResourceException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/createdEmployee")
	public ResponseEntity<EmployeesDto> createEnterprise(@RequestBody EmployeesDto employee) {
		try {
			return new ResponseEntity(employeeService.createEmployee(employee), HttpStatus.OK);
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<EmployeesDto> updateEnterprise(@PathVariable Long id,
			@RequestBody EmployeesDto employee) {
		try {
			return new ResponseEntity(employeeService.updateEmployee(id, employee), HttpStatus.OK);
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(HttpStatus.NOT_MODIFIED);
		}

	}

}
