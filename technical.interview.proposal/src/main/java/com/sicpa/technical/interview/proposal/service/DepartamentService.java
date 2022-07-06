package com.sicpa.technical.interview.proposal.service;

import java.util.List;

import com.sicpa.technical.interview.proposal.dto.DepartamentDto;
import com.sicpa.technical.interview.proposal.dto.MessageDto;
import com.sicpa.technical.interview.proposal.exceptions.ResourceException;

public interface DepartamentService {

	List<DepartamentDto> getAllDepartaments() throws ResourceException;

	MessageDto createDepartaments(DepartamentDto departament) throws ResourceException;

	MessageDto updateDepartaments(Long id, DepartamentDto departament) throws ResourceException;
}
