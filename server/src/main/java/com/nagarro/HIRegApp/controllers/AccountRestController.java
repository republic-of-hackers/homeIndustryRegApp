package com.nagarro.HIRegApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nagarro.HIRegApp.models.AccountInformation;
import com.nagarro.HIRegApp.models.UserCredential;
import com.nagarro.HIRegApp.models.UserSession;

import com.nagarro.HIRegApp.repositories.AccountInformationRepo;
import com.nagarro.HIRegApp.repositories.UserCredentialRepo;
import com.nagarro.HIRegApp.repositories.UserSessionRepo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/account")
public class AccountRestController {
	
	@Autowired
	AccountInformationRepo accInfoRepo;
	
	@Autowired
	private UserCredentialRepo userCredentialRepo;
	
	@Autowired
	private UserSessionRepo userSessionRepo;
	
	@PostMapping("/addAccount")
	public ResponseEntity<String> addAccount(@RequestBody AccountInformation accInfo, @RequestParam("session")String sessionToken) {
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		if(currentUserSession != null) {
			UserCredential existUserCredentail = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			accInfo.setUser_cred(existUserCredentail);
			existUserCredentail.setAccInfo(accInfo);
			accInfoRepo.save(accInfo);
			return new ResponseEntity<String>("created account info", HttpStatus.OK);
		} else
			return new ResponseEntity<String>("Expired Session", HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/isAdded")
	public boolean statusAccount(@RequestParam("session") String sessionToken) {
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		if(currentUserSession != null) {
			UserCredential existUserCredential = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			AccountInformation existAccInfo = existUserCredential.getAccInfo();
			if(existAccInfo != null)
				return true;
			else
				return false;
		} else 
			return false; 
	}

}
