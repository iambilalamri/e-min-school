package com.bamri.school.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "SCHOOL")
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateEtablissement;

	@OneToMany(mappedBy = "school")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Student> students;

	@OneToMany(mappedBy = "school")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Formation> formations;

	@OneToMany(mappedBy = "school")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Collection<Diplome> diplomes;
}
