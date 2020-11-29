package com.bamri.school.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bamri.school.entities.Filiere;
import com.bamri.school.entities.Student;
import com.bamri.school.repository.FiliereRepository;
import com.bamri.school.repository.SchoolRepository;
import com.bamri.school.repository.StudentRepository;

@RestController
@RequestMapping(value = "/api/v1")
public class StudentController {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	FiliereRepository filiereRepository;

	@Autowired
	SchoolRepository schoolRepository;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/students")
	public List<Student> getStudents() {
		return this.studentRepository.findAll();
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable("id") Long id) {
		return this.studentRepository.findById(id).get();
	}

	@PostMapping("/student")
	public void addStudent(@RequestBody Student student, @RequestParam("filiere") String filiereName) {

		Optional<Filiere> filiere = this.filiereRepository.findByName(filiereName);
		student.setFiliere(filiere.get());
		this.studentRepository.save(student);
	}
}
