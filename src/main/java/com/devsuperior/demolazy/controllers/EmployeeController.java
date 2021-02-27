package com.devsuperior.demolazy.controllers;

import java.util.List;

import com.devsuperior.demolazy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.demolazy.dto.EmployeeDTO;
import com.devsuperior.demolazy.services.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<EmployeeDTO> findById(@PathVariable Long id) {
		EmployeeDTO obj = service.findById(id);
		return ResponseEntity.ok(obj);
	}
	 
	@GetMapping
	public ResponseEntity<List<EmployeeDTO>> findAll() {
		List<EmployeeDTO> list = service.findAll();
		return ResponseEntity.ok(list);
	}

	@PostMapping
	public ResponseEntity<EmployeeDTO> save(@RequestBody EmployeeDTO objDto) {
		EmployeeDTO objDTO = service.save(new Employee(objDto.getId(),objDto.getName()));
		return ResponseEntity.ok(objDTO);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteById(@PathVariable  Long id) throws Exception {
		service.deleteByID(id);
	}
}
