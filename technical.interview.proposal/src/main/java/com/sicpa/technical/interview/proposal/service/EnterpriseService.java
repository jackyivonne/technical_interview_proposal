package com.sicpa.technical.interview.proposal.service;

import java.util.List;

import com.sicpa.technical.interview.proposal.dto.EnterpriseDto;
import com.sicpa.technical.interview.proposal.exceptions.ResourceException;

public interface EnterpriseService {

	List<EnterpriseDto> getAllEnterprises() throws ResourceException;

	EnterpriseDto createEnterprise(EnterpriseDto enterprises) throws ResourceException;

	EnterpriseDto updateEnterprise(Long id, EnterpriseDto enterprises) throws ResourceException;

}
