package com.nagarro.HIRegApp.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nagarro.HIRegApp.models.InspectionInformation;
import com.nagarro.HIRegApp.models.InspectionTimeSlot;

public interface InspectionTimeSlotRepo extends CrudRepository<InspectionTimeSlot, Long> {
	InspectionTimeSlot findByInspInfo(InspectionInformation inspInfo);

}
