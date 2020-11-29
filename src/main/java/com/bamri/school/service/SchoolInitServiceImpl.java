package com.bamri.school.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bamri.school.entities.Diplome;
import com.bamri.school.entities.Filiere;
import com.bamri.school.entities.Formation;
import com.bamri.school.entities.Matiere;
import com.bamri.school.entities.School;
import com.bamri.school.entities.Student;
import com.bamri.school.repository.DiplomeRepository;
import com.bamri.school.repository.FiliereRepository;
import com.bamri.school.repository.FormationRepository;
import com.bamri.school.repository.MatiereRepository;
import com.bamri.school.repository.SchoolRepository;
import com.bamri.school.repository.StudentRepository;

@Service
public class SchoolInitServiceImpl implements ISchoolInitService {

	@Autowired
	SchoolRepository schoolRepository;

	@Autowired
	MatiereRepository matiereRepository;

	@Autowired
	FormationRepository formationRepository;

	@Autowired
	FiliereRepository filiereRepository;

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	DiplomeRepository diplomeRepository;

	@Override
	public void initSchool() {
		// TODO Auto-generated method stub
		// , "ISTA", "ESTO", "INSA"
		Stream.of("ENSISA", "ESTO").forEach(schoolName -> {
			try {
				School school = new School();
				school.setDateEtablissement(new SimpleDateFormat("dd-MM-yyyy").parse("11-05-1996"));
				school.setName(schoolName);
				schoolRepository.save(school);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	public void initFormation() {
		// TODO Auto-generated method stub
		// , "Droit Economie Gestion", "Santé", "Sciences et Technologies", "Sport",
		// "Sciences Humaines et Sociales"
		schoolRepository.findAll().forEach(school -> {
			Stream.of("Sciences et Technologies", "Sante").forEach(formName -> {
				Formation formation = new Formation();
				formation.setName(formName);
				formation.setSchool(school);
				formationRepository.save(formation);
			});
		});
	}

	@Override
	public void initFiliere() {
		// TODO Auto-generated method stub
		// , "Automatique & Systemes Embarques", "Textile & Fibre", "Mecanique", "Genie
		// Industriel"
		formationRepository.findAll().forEach(formation -> {
			Stream.of("Information & Réseaux").forEach(filiereName -> {
				Filiere filiere = new Filiere();
				filiere.setName(filiereName);
				filiere.setFormation(formation);
				filiereRepository.save(filiere);
			});
		});

	}

	@Override
	public void initMatiere() {
		// TODO Auto-generated method stub
		filiereRepository.findAll().forEach(filiere -> {
			Stream.of("Mathematique pour l'informatique", "Calcul matriciel, analyse générale, statistiques",
					"Analyse numérique et calcul scientifique", "Programmation fonctionnelle").forEach(matiereName -> {
						Matiere matiere = new Matiere();
						matiere.setName(matiereName);
						matiere.setNumberOfHours(40);
						matiere.setProject(false);
						matiere.setFiliere(filiere);
						matiereRepository.save(matiere);
					});
		});

	}

	@Override
	public void initStudent() {
		// TODO Auto-generated method stub
		schoolRepository.findAll().forEach(school -> {
			Stream.of("Bilal AMRI").forEach(studentName -> {
				Student student = new Student();
				student.setFirstName(studentName);
				student.setLastName(studentName);
				student.setEmail(studentName + "@gmail.com");
				student.setDateBirth(new Date());
				student.setCurrentYear(1);
				student.setFirstYears(new Date());
				student.setImageURL("https://cache.marieclaire.fr/data/photo/w1000_ci/1bi/formaul-cv.jpg");
				student.setSchool(school);
				studentRepository.save(student);
			});
		});
	}

	@Override
	public void initDiplome() {
		// TODO Auto-generated method stub
		schoolRepository.findAll().forEach(school -> {
			Stream.of("Licence", "Master", "Ingenierie").forEach(diplomeName -> {
				Diplome diplome = new Diplome();
				diplome.setName(diplomeName);
				diplome.setDateDelivery(new Date());
				diplome.setSchool(school);
				diplomeRepository.save(diplome);
			});
		});
	}

}
