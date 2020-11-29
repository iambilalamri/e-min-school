package com.bamri.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.bamri.school.service.ISchoolInitService;

@EnableJpaRepositories
@SpringBootApplication
public class EMiniSchoolApplication {

	@Autowired
	ISchoolInitService schoolInitService;

	public static void main(String[] args) {
		SpringApplication.run(EMiniSchoolApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		// TODO Auto-generated method stub
//		schoolInitService.initSchool();
//		schoolInitService.initFormation();
//		schoolInitService.initFiliere();
//		schoolInitService.initModule();
//		schoolInitService.initMatiere();
//		schoolInitService.initStudent();
//		schoolInitService.initDiplome();
//	}

}
