package com.sicpa.technical.interview.proposal.service;

import java.util.List;

import com.sicpa.technical.interview.proposal.dto.EmployeesDto;
import com.sicpa.technical.interview.proposal.exceptions.ResourceException;

public interface EmployeeService {

	List<EmployeesDto> getAllEmployees() throws ResourceException;

	EmployeesDto createEmployee(EmployeesDto employee) throws ResourceException;

	EmployeesDto updateEmployee(Long id, EmployeesDto employee) throws ResourceException;

}
