package com.nagarro.HIRegApp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.HIRegApp.models.InspectionInformation;

public interface InspectionInformationRepo extends JpaRepository<InspectionInformation, Long> {
	
	List<InspectionInformation> findByCity(final String city);

}
