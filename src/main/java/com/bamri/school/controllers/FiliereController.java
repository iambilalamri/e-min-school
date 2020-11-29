package com.bamri.school.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bamri.school.entities.Filiere;
import com.bamri.school.entities.Formation;
import com.bamri.school.repository.FiliereRepository;
import com.bamri.school.repository.FormationRepository;

@RestController
@RequestMapping(value = "/api/v1")
public class FiliereController {

	@Autowired
	private FiliereRepository filiereRepository;

	@Autowired
	FormationRepository formationRepository;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/filieres")
	public ResponseEntity<List<Filiere>> getFilieres() {
		return ResponseEntity.status(HttpStatus.OK).body(filiereRepository.findAll());
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/filieres/{id}")
	public ResponseEntity<Filiere> getFiliere(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(filiereRepository.findById(id).get());
	}

	@PostMapping("/filiere")
	public ResponseEntity<Void> addFiliere(@RequestBody Filiere filiere, @RequestParam("formation") String code) {
		Optional<Formation> formation = formationRepository.findByCode(code);
		if (formation.isPresent())
			return null;
		filiere.setFormation(formation.get());
		filiereRepository.save(filiere);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
