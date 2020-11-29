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

import com.bamri.school.entities.Diplome;
import com.bamri.school.entities.Filiere;
import com.bamri.school.entities.School;
import com.bamri.school.exception.DiplomeNotFoundException;
import com.bamri.school.exception.FiliereNotFoundException;
import com.bamri.school.repository.DiplomeRepository;
import com.bamri.school.repository.FiliereRepository;
import com.bamri.school.repository.SchoolRepository;

@RestController
@RequestMapping(value = "/api/v1")
public class DiplomeController {

	@Autowired
	private DiplomeRepository diplomeRepository;

	@Autowired
	private FiliereRepository filiereRepository;

	@Autowired
	private SchoolRepository schoolRepository;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/diplomes")
	public ResponseEntity<List<Diplome>> getDiplomes() {
		return ResponseEntity.status(HttpStatus.OK).body(diplomeRepository.findAll());
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/diplomes/{id}")
	public ResponseEntity<Diplome> getDiplome(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(diplomeRepository.findById(id)
				.orElseThrow(() -> new DiplomeNotFoundException("No diplome found with id:" + id)));
	}

	@PostMapping("/diplome")
	public void addDiplome(@RequestBody Diplome diplome, @RequestParam("filiere") String fname,
			@RequestParam("school") String sname) {
		Filiere filiere = this.filiereRepository.findByName(fname)
				.orElseThrow(() -> new FiliereNotFoundException("Filiere is not found"));
		Optional<School> school = this.schoolRepository.findByName(sname);
		diplome.setSchool(school.get());
		diplome.setFiliere(filiere);
		this.diplomeRepository.save(diplome);
	}
}
