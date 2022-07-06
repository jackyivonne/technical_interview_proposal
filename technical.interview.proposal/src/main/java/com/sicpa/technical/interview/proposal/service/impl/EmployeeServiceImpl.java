package com.sicpa.technical.interview.proposal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicpa.technical.interview.proposal.dto.EmployeesDto;
import com.sicpa.technical.interview.proposal.exceptions.ResourceException;
import com.sicpa.technical.interview.proposal.models.Employees;
import com.sicpa.technical.interview.proposal.repository.EmployeeRepository;
import com.sicpa.technical.interview.proposal.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<EmployeesDto> getAllEmployees() throws ResourceException {
		// TODO Auto-generated method stub

		List<Employees> listBdd = employeeRepository.findAll();
		List<EmployeesDto> employeesDtos = new ArrayList<>();
		listBdd.stream().forEach((o) -> {
			employeesDtos.add(new EmployeesDto(o.getId(), o.getCreatedBy(), o.getCreatedDate(), o.getModifiedBy(),
					o.getModifiedDate(), o.getStatus(), o.getAge(), o.getEmail(), o.getName(), o.getPosition(),
					o.getSurname()));
		});

		return employeesDtos;
	}

	@Override
	public EmployeesDto createEmployee(EmployeesDto employee) throws ResourceException {
		// TODO Auto-generated method stub
		try {
			Employees employeesBdd = new Employees();
			employeesBdd.setCreatedBy(employee.getCreatedBy());
			employeesBdd.setCreatedDate(employee.getCreatedDate());
			employeesBdd.setModifiedBy(employee.getModifiedBy());
			employeesBdd.setModifiedDate(employee.getModifiedDate());
			employeesBdd.setStatus(employee.getStatus());
			employeesBdd.setAge(employee.getAge());
			employeesBdd.setEmail(employee.getEmail());
			employeesBdd.setName(employee.getName());
			employeesBdd.setPosition(employee.getPosition());
			employeesBdd.setSurname(employee.getSurname());			
			employeeRepository.save(employeesBdd);
			employee.setId(employeesBdd.getId());
			return employee;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceException("Error al guardar Employees", e);
		}
	}

	@Override
	public EmployeesDto updateEmployee(Long id, EmployeesDto employee) throws ResourceException {
		// TODO Auto-generated method stub
		Optional<Employees> employeesOptional = employeeRepository.findById(id);
		if (employeesOptional.isPresent()) {
			Employees employeeBdd = employeesOptional.get();
			employeeBdd.setCreatedBy(employee.getCreatedBy());
			employeeBdd.setCreatedDate(employee.getCreatedDate());
			employeeBdd.setModifiedBy(employee.getModifiedBy());
			employeeBdd.setModifiedDate(employee.getModifiedDate());
			employeeBdd.setStatus(employee.getStatus());
			employeeBdd.setAge(employee.getAge());
			employeeBdd.setEmail(employee.getEmail());
			employeeBdd.setName(employee.getName());
			employeeBdd.setPosition(employee.getPosition());
			employeeBdd.setSurname(employee.getSurname());
			employeeRepository.save(employeeBdd);
			employee.setId(id);
			return employee;
		} else {
			throw new ResourceException("No se encuentra el empleado con id: " + id);
		}
	}

}
