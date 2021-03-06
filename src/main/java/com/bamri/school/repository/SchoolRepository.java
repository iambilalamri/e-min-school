package com.bamri.school.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.bamri.school.entities.School;

@RepositoryRestResource
@CrossOrigin("*")
public interface SchoolRepository extends JpaRepository<School, Long>{

	Optional<School> findByName(String name);

}
