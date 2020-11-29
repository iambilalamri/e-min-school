package com.bamri.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bamri.school.entities.Formation;
import com.bamri.school.entities.School;
import com.bamri.school.repository.FormationRepository;
import com.bamri.school.repository.SchoolRepository;

@RestController
@RequestMapping(value = "/api/v1")
public class FormationController {

	@Autowired
	private FormationRepository formationRepository;

	@Autowired
	private SchoolRepository schoolRepository;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/formations")
	public List<Formation> getFilieres() {
		return this.formationRepository.findAll();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/formations/{id}")
	public Formation getFormation(@PathVariable("id") Long id) {
		return this.formationRepository.findById(id).get();
	}

	@PostMapping("/formation")
	public void addFormation(@RequestBody Formation formation, @RequestParam("school") String name) {
		School school = this.schoolRepository.findByName(name).get();
		formation.setSchool(school);
		this.formationRepository.save(formation);
	}
}
