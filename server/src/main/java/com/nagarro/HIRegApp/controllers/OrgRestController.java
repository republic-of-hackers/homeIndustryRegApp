package com.nagarro.HIRegApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nagarro.HIRegApp.models.OrganisationInformation;
import com.nagarro.HIRegApp.models.UserCredential;
import com.nagarro.HIRegApp.models.UserSession;

import com.nagarro.HIRegApp.repositories.OrganisationInformationRepo;
import com.nagarro.HIRegApp.repositories.UserCredentialRepo;
import com.nagarro.HIRegApp.repositories.UserSessionRepo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/organisation")
public class OrgRestController {
	
	@Autowired
	OrganisationInformationRepo orgInfoRepo;
	
	@Autowired
	private UserCredentialRepo userCredentialRepo;
	
	@Autowired
	private UserSessionRepo userSessionRepo;
	
	@PostMapping("/addOrg")
	public ResponseEntity<String> 
		addOrganisation(
				@RequestBody OrganisationInformation orgInfo, 
				@RequestParam("session")String sessionToken) 
	{
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		if(currentUserSession != null) {
			UserCredential existUserCredentail = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			orgInfo.setUser_cred(existUserCredentail);
			existUserCredentail.setOrgInfo(orgInfo);
			orgInfoRepo.save(orgInfo);
			return new ResponseEntity<String>("created organisation info", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("Expired Session", HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/isOrg")
	public boolean 
		isOrg(
			@RequestParam("session") String sessionToken)
	{
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		if(currentUserSession != null) {
			UserCredential existUserCredentail = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			String userType = existUserCredentail.getUserPersonal().getUser_type();
			if(userType.equalsIgnoreCase("organisation")) {
				return true;
			} else
				return false;
		} else
			return false;
	}
	
	@GetMapping("/isAdded")
	public boolean statusOrganisation(@RequestParam("session") String sessionToken) {
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		if(currentUserSession != null) {
			UserCredential existUserCredential = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			OrganisationInformation existOrganisation = existUserCredential.getOrgInfo();
			if(existOrganisation != null)
				return true;
			else
				return false;
		} else 
			return false; 
	}

}
