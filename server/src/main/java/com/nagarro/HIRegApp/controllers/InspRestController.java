package com.nagarro.HIRegApp.controllers;

import java.sql.Date;
import java.sql.Time;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.HIRegApp.models.InspectionInformation;
import com.nagarro.HIRegApp.models.InspectionTimeSlot;
import com.nagarro.HIRegApp.models.UserCredential;
import com.nagarro.HIRegApp.models.UserSession;

import com.nagarro.HIRegApp.repositories.InspectionInformationRepo;
import com.nagarro.HIRegApp.repositories.InspectionTimeSlotRepo;
import com.nagarro.HIRegApp.repositories.UserCredentialRepo;
import com.nagarro.HIRegApp.repositories.UserSessionRepo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/insp")
public class InspRestController {
	
	@Autowired
	private InspectionInformationRepo inspInfoRepo;
	
	@Autowired
	private InspectionTimeSlotRepo inspTimeSlotRepo;
	
	@Autowired
	private UserCredentialRepo userCredentialRepo;
	
	@Autowired
	private UserSessionRepo userSessionRepo;
	
	@GetMapping("/getAvailableDates")
	public ResponseEntity<List<Date>>
		getAvailableDatesByCity(
				@RequestParam("city") String city,
				@RequestParam("session")String sessionToken)
	{
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		List<Date> slots = new ArrayList<Date>();
		if(currentUserSession != null) {
			UserCredential existUserCredential = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			if(existUserCredential != null) {
				List<InspectionInformation> inspInfoList = inspInfoRepo.findByCity(city.toLowerCase());
				for(InspectionInformation inspInfo: inspInfoList) {
					slots.add(inspInfo.getAvailableDate());
				}
				return new ResponseEntity<List<Date>>(slots, HttpStatus.OK);
			}
			return new ResponseEntity<List<Date>>(slots, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Date>>(slots, HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/getTimeSlots")
	public ResponseEntity<List<Time>> 
		getTimeSlotsByCity(
				@RequestParam("date") Date date,
				@RequestParam("city") String city,
				@RequestParam("session")String sessionToken)
	{	
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		List<Time> slots = new ArrayList<Time>();
		if(currentUserSession != null) {
			UserCredential existUserCredential = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			if(existUserCredential != null) {
				List<InspectionInformation> inspInfoList = inspInfoRepo.findByCity(city);
				for(InspectionInformation inspInfo: inspInfoList) {
					System.out.println("City: " + inspInfo.getCity());
					System.out.println("Date: " + inspInfo.getAvailableDate());
					if(inspInfo.getAvailableDate().equals(date)) {
						System.out.println("success match");
						InspectionTimeSlot timeSlots = inspTimeSlotRepo.findByInspInfo(inspInfo);
						slots.add(timeSlots.getSlot1());
						slots.add(timeSlots.getSlot2());
						slots.add(timeSlots.getSlot3());
					}
				}
				return new ResponseEntity<List<Time>>(slots, HttpStatus.OK);
			}
			return new ResponseEntity<List<Time>>(slots, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Time>>(slots, HttpStatus.FORBIDDEN);
	}
	
	@PostMapping("/bookSlot")
	public ResponseEntity<String> 
		bookTimeSlot(
				@RequestParam("session")String sessionToken,
				@RequestParam("date") Date date,
				@RequestParam("city") String city,
				@RequestParam("time") Time time)
	{
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		if(currentUserSession != null) {
			UserCredential existUserCredential = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			if(existUserCredential != null) {
				List<InspectionInformation> inspInfoList = inspInfoRepo.findByCity(city);
				for(InspectionInformation inspInfo: inspInfoList) {
					if(inspInfo.getAvailableDate().equals(date)) {
						System.out.println("success match");
						InspectionTimeSlot timeSlots = inspTimeSlotRepo.findByInspInfo(inspInfo);
						if(timeSlots.getSlot1().equals(time)) {
							System.out.println("Time Slot Booked For 1");
							timeSlots.setSlot1BookBy(existUserCredential);
						} else if(timeSlots.getSlot2().equals(time)) {
							System.out.println("Time Slot Booked For 2");
							timeSlots.setSlot2BookBy(existUserCredential);
						} else if(timeSlots.getSlot3().equals(time)) {
							System.out.println("Time Slot Booked For 3");
							timeSlots.setSlot3BookBy(existUserCredential);
						}
						inspTimeSlotRepo.save(timeSlots);
						existUserCredential.setInspInfo(inspInfo);
						userCredentialRepo.save(existUserCredential);
						break;
					}
				}
				
				return new ResponseEntity<String>("Time Slot Booked Success.", HttpStatus.OK);
			}
			return new ResponseEntity<String>("User Not Found.", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Session Expired.", HttpStatus.FORBIDDEN);
		
	}
	
	@GetMapping("/isAdded")
	public boolean 
		statusExperience(
			@RequestParam("session") String sessionToken) 
	{
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		if(currentUserSession != null) {
			UserCredential existUserCredential = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			InspectionInformation existInspInfo = existUserCredential.getInspInfo();
			if(existInspInfo != null)
				return true;
			else
				return false;
		} else 
			return false; 
	}

}
