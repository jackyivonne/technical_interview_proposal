package com.sicpa.technical.interview.proposal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicpa.technical.interview.proposal.models.Departaments;

public interface DepartamentRepository extends JpaRepository<Departaments, Long> {

}
