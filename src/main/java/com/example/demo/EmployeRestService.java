package com.example.demo;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class EmployeRestService {
	@Autowired
	EmployeRepo employeRepository;

	// http://localhost:8080/listEmployes
	@GetMapping(value = "/listEmployes")
	public List<Employe> getAllEmp() {
		return employeRepository.findAll();
	}

	@GetMapping(value = "/listEmployes/{id}")
	public Employe getEmp(@PathVariable(name = "id") Long id) {
		return employeRepository.findById(id).get();
	}

	@PutMapping(value = "/listEmployes/{id}")
	public Employe updateEmp(@PathVariable(name = "id") Long id, @RequestBody Employe emp) {
		emp.setId(id);
		return employeRepository.save(emp);
	}

	@PostMapping(value = "/listEmployes")
	public Employe save(@RequestBody Employe emp) {
		return employeRepository.save(emp);
	}

	@DeleteMapping(value = "/deleteById/{id}")
	public void delete(@PathVariable(name = "id") Long id) {
		employeRepository.deleteById(id);
	}

	@DeleteMapping(value = "/deleteByNom/{nom}")
	public void delete(@PathVariable(name = "nom") String nom) {
		for (Employe employe : employeRepository.findByNomContains(nom)) {
			employeRepository.deleteById(employe.getId());
		}
	}

	
	//ca marche avec le passage du parametre en textPlain
	@DeleteMapping(value = "/deleteByNom")
	public void deleteByNom( @RequestBody String nom) {
		for (Employe employe : employeRepository.findByNomContains(nom)) {
			employeRepository.deleteById(employe.getId());
		}

	}
}
