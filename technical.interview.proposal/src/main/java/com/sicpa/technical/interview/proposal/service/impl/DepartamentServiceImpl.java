package com.sicpa.technical.interview.proposal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicpa.technical.interview.proposal.dto.DepartamentDto;
import com.sicpa.technical.interview.proposal.dto.EnterpriseDto;
import com.sicpa.technical.interview.proposal.dto.MessageDto;
import com.sicpa.technical.interview.proposal.exceptions.ResourceException;
import com.sicpa.technical.interview.proposal.models.Departaments;
import com.sicpa.technical.interview.proposal.models.Enterprises;
import com.sicpa.technical.interview.proposal.repository.DepartamentRepository;
import com.sicpa.technical.interview.proposal.repository.EnterpriseRepository;
import com.sicpa.technical.interview.proposal.service.DepartamentService;

@Service
public class DepartamentServiceImpl implements DepartamentService {

	@Autowired
	private DepartamentRepository departamentRepository;
	@Autowired
	private EnterpriseRepository enterprisesRepository;

	@Override
	public List<DepartamentDto> getAllDepartaments() throws ResourceException {
		// TODO Auto-generated method stub

		List<Departaments> listBdd = departamentRepository.findAll();
		List<DepartamentDto> departamentDtos = new ArrayList<>();

		listBdd.stream().forEach((o) -> {
			departamentDtos.add(new DepartamentDto(o.getId(), o.getCreatedBy(), o.getCreatedDate(), o.getModifiedBy(),
					o.getModifiedDate(), o.getStatus(), o.getDescription(), o.getName(), o.getPhone(),
					new EnterpriseDto(o.getEnterprises().getId(), o.getEnterprises().getCreatedBy(),
							o.getEnterprises().getCreatedDate(), o.getEnterprises().getModifiedBy(),
							o.getEnterprises().getModifiedDate(), o.getEnterprises().getStatus(),
							o.getEnterprises().getAddress(), o.getEnterprises().getName(),
							o.getEnterprises().getPhone())));
		});
		return departamentDtos;

	}

	@Override
	public MessageDto createDepartaments(DepartamentDto departament) throws ResourceException {
		// TODO Auto-generated method stub
		try {

			Enterprises e = enterprisesRepository.findById(departament.getEnterprise().getId()).orElseThrow(
					() -> new ResourceException("No existe Enterprise con id:" + departament.getEnterprise().getId()));

			Departaments departamentBdd = new Departaments(null, departament.getCreatedBy(),
					departament.getCreatedDate(), departament.getModifiedBy(), departament.getModifiedDate(),
					departament.getStatus(), departament.getDescription(), departament.getName(),
					departament.getPhone(), e, null);
			departamentRepository.save(departamentBdd);
			return new MessageDto("Registro creado con exito");
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResourceException("Error al guardar Departaments", e);
		}

	}

	@Override
	public MessageDto updateDepartaments(Long id, DepartamentDto departament) throws ResourceException {
		Optional<Departaments> departamentOptional = departamentRepository.findById(id);
		if (departamentOptional.isPresent()) {
			departamentOptional.get().setCreatedBy(departament.getCreatedBy());
			departamentOptional.get().setCreatedDate(departament.getCreatedDate());
			departamentOptional.get().setModifiedBy(departament.getModifiedBy());
			departamentOptional.get().setModifiedDate(departament.getModifiedDate());
			departamentOptional.get().setStatus(departament.getStatus());
			departamentOptional.get().setDescription(departament.getDescription());
			departamentOptional.get().setName(departament.getName());
			departamentOptional.get().setPhone(departament.getPhone());
			departamentRepository.save(departamentOptional.get());
			return new MessageDto("Registro actualizado con exito: " + id);
		} else {
			throw new ResourceException("No se encuentra el departamento con id: " + id);
		}

	}

}
