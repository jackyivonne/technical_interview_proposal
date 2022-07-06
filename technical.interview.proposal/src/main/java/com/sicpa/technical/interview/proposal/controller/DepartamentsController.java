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

import com.sicpa.technical.interview.proposal.dto.DepartamentDto;
import com.sicpa.technical.interview.proposal.dto.MessageDto;
import com.sicpa.technical.interview.proposal.exceptions.ResourceException;
import com.sicpa.technical.interview.proposal.service.DepartamentService;

@RestController
@RequestMapping("/departament")
public class DepartamentsController {

	@Autowired
	private DepartamentService departamentService;

	@GetMapping("/listDepartaments")
	public ResponseEntity<List> getAllDepartaments() {
		try {
			return new ResponseEntity(departamentService.getAllDepartaments(), HttpStatus.OK);
		} catch (ResourceException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/createdDepartament")
	public ResponseEntity<MessageDto> createDepartament(@RequestBody DepartamentDto departament) {
		try {
			return new ResponseEntity(departamentService.createDepartaments(departament), HttpStatus.OK);
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateDepartament/{id}")
	public ResponseEntity<MessageDto> updateDepartaments(@PathVariable Long id,
			@RequestBody DepartamentDto departament) {
		try {
			return new ResponseEntity(departamentService.updateDepartaments(id, departament), HttpStatus.OK);
		} catch (ResourceException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new ResponseEntity(HttpStatus.NOT_MODIFIED);
		}

	}

}
