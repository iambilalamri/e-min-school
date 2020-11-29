package com.bamri.school.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "FILIERE")
public class Filiere {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private int dureeFormation;

	@ManyToOne
	private Formation formation;
	
	@OneToMany(mappedBy = "filiere")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Matiere> matieres;
	
	@OneToMany(mappedBy = "filiere")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Student> students;
	
	@OneToMany(mappedBy = "filiere")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Diplome> diplomes;
	
}
