package com.bamri.school.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bamri.school.entities.School;
import com.bamri.school.repository.SchoolRepository;

@RestController
@RequestMapping(value = "/api/v1")
public class SchoolController {

	@Autowired
	private SchoolRepository schoolRepository;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/schools")
	public List<School> getSchools() {
		return this.schoolRepository.findAll();
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/schools/{id}")
	public School getSchool(@PathVariable("id") Long id) {
		return this.schoolRepository.findById(id).get();
	}

	@PostMapping("/school")
	public void addSchool(@RequestBody School school) {
		this.schoolRepository.save(school);
	}

}
