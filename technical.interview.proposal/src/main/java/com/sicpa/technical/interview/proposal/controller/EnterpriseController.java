package com.sicpa.technical.interview.proposal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sicpa.technical.interview.proposal.dto.EnterpriseDto;
import com.sicpa.technical.interview.proposal.exceptions.ResourceException;
import com.sicpa.technical.interview.proposal.service.EnterpriseService;

@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

	@Autowired
	private EnterpriseService enterpriseService;

	@GetMapping("/listEnterprise")
	public ResponseEntity<List> getAllEnterprises() {
		try {
			return new ResponseEntity(enterpriseService.getAllEnterprises(), HttpStatus.OK);
		} catch (ResourceException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/createdEnterprise")
	public ResponseEntity<EnterpriseDto> createEnterprise(@RequestBody EnterpriseDto enterprise) {
		try {
			return new ResponseEntity(enterpriseService.createEnterprise(enterprise), HttpStatus.OK);
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateEnterprise/{id}")
	public ResponseEntity<EnterpriseDto> updateEnterprise(@PathVariable Long id, @RequestBody EnterpriseDto enterprise) {
		try {
			return new ResponseEntity(enterpriseService.updateEnterprise(id, enterprise), HttpStatus.OK);
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(HttpStatus.NOT_MODIFIED);
		}

	}

}
