package com.sicpa.technical.interview.proposal;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import com.sicpa.technical.interview.proposal.dto.EnterpriseDto;
import com.sicpa.technical.interview.proposal.exceptions.ResourceException;
import com.sicpa.technical.interview.proposal.models.Enterprises;
import com.sicpa.technical.interview.proposal.repository.EnterpriseRepository;
import com.sicpa.technical.interview.proposal.service.impl.EnterpriseServiceImpl;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class EnterpriseServiceImplTest {

	@MockBean
	private EnterpriseRepository enterpriseRepository;

	@InjectMocks
	private EnterpriseServiceImpl enterpriseService;

	private EnterpriseDto enterpriseDto;

	@BeforeEach
	private void inicialize() {
		enterpriseDto = new EnterpriseDto();
		enterpriseDto.setAddress("Direccion");
		enterpriseDto.setName("SICPA");
		enterpriseDto.setAddress("Norte");
		enterpriseDto.setPhone("123456");
		enterpriseDto.setStatus(true);

	}

	@Test
	public void givenEnterpriseDto_whenSaveASEnterpriseRequest_thenSaveEnterpriseInDb() throws ResourceException {
		Mockito.when(enterpriseRepository.save(Mockito.any(Enterprises.class)))
				.thenReturn(new Enterprises(1L, null, null, null, null, null, null, null, null, null));
		EnterpriseDto response = enterpriseService.createEnterprise(enterpriseDto);
		Assert.notNull(response, "No tiene elemento");

	}

	@Test
	public void givenEnterpriseDto_whenSaveAEnterpriseRequestAndBddProblems_thenResourceException()
			throws ResourceException {
		Mockito.when(enterpriseRepository.save(Mockito.any(Enterprises.class)))
				.thenThrow(new RuntimeException("Something error with bdd"));
		ResourceException exception = null;
		try {
			enterpriseService.createEnterprise(enterpriseDto);
		} catch (ResourceException e) {
			// TODO: handle exception
			exception = e;
		}
		Assert.notNull(exception, "No se produjo la exception esperada");

	}
	
	@Test
	public void givenListDto_whenFindAllEnterpriseRequest_thenListEnterpriseInDb() throws ResourceException {
		List<Enterprises> lista = new ArrayList<>();
		Enterprises enterprises = new Enterprises(1L, null, null, null, null, null, null, null, null, null);
		lista.add(enterprises);
		Mockito.when(enterpriseRepository.findAll()).thenReturn(lista);			
		List<EnterpriseDto> response = enterpriseService.getAllEnterprises();
		Assert.notNull(response, "No tiene elemento");

	}

}
