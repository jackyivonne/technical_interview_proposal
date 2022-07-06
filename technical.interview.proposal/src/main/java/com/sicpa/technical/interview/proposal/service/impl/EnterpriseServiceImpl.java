package com.sicpa.technical.interview.proposal.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicpa.technical.interview.proposal.dto.EnterpriseDto;
import com.sicpa.technical.interview.proposal.exceptions.ResourceException;
import com.sicpa.technical.interview.proposal.models.Enterprises;
import com.sicpa.technical.interview.proposal.repository.EnterpriseRepository;
import com.sicpa.technical.interview.proposal.service.EnterpriseService;

@Service
public class EnterpriseServiceImpl implements EnterpriseService {

	@Autowired
	EnterpriseRepository enterpriseRepository;

	@Override
	public List<EnterpriseDto> getAllEnterprises() throws ResourceException {
		// TODO Auto-generated method stub
		List<Enterprises> listBdd = enterpriseRepository.findAll();
		return listBdd.stream()
				.map(o -> new EnterpriseDto(o.getId(), o.getCreatedBy(), o.getCreatedDate(), o.getModifiedBy(),
						o.getModifiedDate(), o.getStatus(), o.getAddress(), o.getName(), o.getPhone()))
				.collect(Collectors.toList());
	}

	@Override
	public EnterpriseDto createEnterprise(EnterpriseDto enterprises) throws ResourceException {
		// TODO Auto-generated method stub
		try {
			Enterprises enterpriseBdd = new Enterprises();
			enterpriseBdd.setCreatedBy(enterprises.getCreatedBy());
			enterpriseBdd.setCreatedDate(enterprises.getCreatedDate());
			enterpriseBdd.setModifiedBy(enterprises.getModifiedBy());
			enterpriseBdd.setModifiedDate(enterprises.getModifiedDate());
			enterpriseBdd.setStatus(enterprises.getStatus());
			enterpriseBdd.setName(enterprises.getName());			
			enterpriseBdd.setAddress(enterprises.getAddress());
			enterpriseBdd.setPhone(enterprises.getPhone());
			enterpriseRepository.save(enterpriseBdd);
			enterprises.setId(enterpriseBdd.getId());
			return enterprises;
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceException("Error al guardar Enterprises", e);
		}

	}

	@Override
	public EnterpriseDto updateEnterprise(Long id, EnterpriseDto enterprises) throws ResourceException {
		// TODO Auto-generated method stub
		Optional<Enterprises> enterpriseOptional = enterpriseRepository.findById(id);
		if (enterpriseOptional.isPresent()) {
			Enterprises enterpriseBdd = enterpriseOptional.get();
			enterpriseBdd.setCreatedBy(enterprises.getCreatedBy());
			enterpriseBdd.setCreatedDate(enterprises.getCreatedDate());
			enterpriseBdd.setModifiedBy(enterprises.getModifiedBy());
			enterpriseBdd.setModifiedDate(enterprises.getModifiedDate());
			enterpriseBdd.setStatus(enterprises.getStatus());
			enterpriseBdd.setName(enterprises.getName());			
			enterpriseBdd.setAddress(enterprises.getAddress());
			enterpriseBdd.setPhone(enterprises.getPhone());			
			enterpriseRepository.save(enterpriseBdd);
			enterprises.setId(id);
			return enterprises;
		} else {
			throw new ResourceException("No se encuentra la empresa con id: " + id);
		}

	}

}
