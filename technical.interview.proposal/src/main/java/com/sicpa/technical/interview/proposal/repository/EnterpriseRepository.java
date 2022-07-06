package com.sicpa.technical.interview.proposal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sicpa.technical.interview.proposal.models.Enterprises;

public interface EnterpriseRepository extends JpaRepository<Enterprises, Long> {

}
