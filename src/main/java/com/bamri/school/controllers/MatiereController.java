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
import com.bamri.school.entities.Matiere;
import com.bamri.school.repository.FiliereRepository;
import com.bamri.school.repository.MatiereRepository;

@RestController
@RequestMapping(value = "/api/v1")
public class MatiereController {

	@Autowired
	private MatiereRepository matiereRepository;

	@Autowired
	FiliereRepository filiereRepository;

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/matieres")
	public List<Matiere> getMatieres() {
		return this.matiereRepository.findAll();
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/matieres/{id}")
	public Matiere getMatiere(@PathVariable("id") Long id) {
		return this.matiereRepository.findById(id).orElse(null);
	}

	@PostMapping("/matiere")
	public void addMatiere(@RequestBody Matiere matiere, @RequestParam("filiere") String name) {
		try {
			Optional<Filiere> filiere = this.filiereRepository.findByName(name);
			if (filiere.isPresent()) {
				matiere.setFiliere(filiere.get());
				this.matiereRepository.save(matiere);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}
}
