package com.sicpa.technical.interview.proposal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicpa.technical.interview.proposal.models.Employees;

public interface EmployeeRepository extends JpaRepository<Employees, Long> {

}
