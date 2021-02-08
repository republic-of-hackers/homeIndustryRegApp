package com.nagarro.HIRegApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nagarro.HIRegApp.models.UserCredential;
import com.nagarro.HIRegApp.models.UserPersonal;
import com.nagarro.HIRegApp.models.UserSession;
import com.nagarro.HIRegApp.repositories.UserCredentialRepo;
import com.nagarro.HIRegApp.repositories.UserPersonalRepo;
import com.nagarro.HIRegApp.repositories.UserSessionRepo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/personal")
public class UserPersonalRestController {
	
	@Autowired
	private UserPersonalRepo userPersonalRepo;
	
	@Autowired
	private UserCredentialRepo userCredentialRepo;
	
	@Autowired
	private UserSessionRepo userSessionRepo;
	
	@PostMapping("/addPersonal")
	public ResponseEntity<String> addPersonalUser(@RequestBody UserPersonal user, @RequestParam("session")String sessionToken) {
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		if(currentUserSession != null) {
			UserCredential existUserCredentail = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			user.setUser_cred(existUserCredentail);
			existUserCredentail.setUserPersonal(user);
			userPersonalRepo.save(user);
			return new ResponseEntity<String>("created user personal", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Expired Session", HttpStatus.FORBIDDEN);
		}
	}
	
	@GetMapping("/isAdded")
	public boolean statusPersonalUser(@RequestParam("session") String sessionToken) {
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		if(currentUserSession != null) {
			UserCredential existUserCredential = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			UserPersonal existUserPersonal = existUserCredential.getUserPersonal();
			if(existUserPersonal != null)
				return true;
			else
				return false;
		} else 
			return false; 
	}
	
	@GetMapping("/getCity")
	public ResponseEntity<String> getCityByCurrentUser(@RequestParam("session") String sessionToken){
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		if(currentUserSession != null) {
			UserCredential existUserCredential = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			if(existUserCredential != null) {
				UserPersonal existUserPersonal = existUserCredential.getUserPersonal();
				return new ResponseEntity<String>(existUserPersonal.getCity(), HttpStatus.OK);
			} else
				return new ResponseEntity<String>("User Not Found", HttpStatus.BAD_REQUEST);
		} else 
			return new ResponseEntity<String>("Session Expired", HttpStatus.FORBIDDEN);
	}

}
