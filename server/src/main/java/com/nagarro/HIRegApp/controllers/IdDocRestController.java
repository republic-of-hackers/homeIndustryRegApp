package com.nagarro.HIRegApp.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nagarro.HIRegApp.models.IdentityDocument;
import com.nagarro.HIRegApp.models.UserCredential;
import com.nagarro.HIRegApp.models.UserSession;

import com.nagarro.HIRegApp.repositories.IdentityDocumentRepo;
import com.nagarro.HIRegApp.repositories.UserCredentialRepo;
import com.nagarro.HIRegApp.repositories.UserSessionRepo;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/identity")
public class IdDocRestController {
	
	@Autowired
	IdentityDocumentRepo idDocRepo;
	
	@Autowired
	private UserCredentialRepo userCredentialRepo;
	
	@Autowired
	private UserSessionRepo userSessionRepo;
	
	@PostMapping("/addIdDoc")
	public ResponseEntity<String> addIdDoc(@RequestParam("session")String sessionToken, @RequestPart("aadhar") MultipartFile aadharFile, @RequestPart("taxid") MultipartFile taxFile) {
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		if(currentUserSession != null) {
			UserCredential existUserCredentail = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			
			IdentityDocument idDoc = new IdentityDocument();
			
			idDoc.setAadhar_file_name(aadharFile.getOriginalFilename());
			idDoc.setAadhar_file_type(aadharFile.getContentType());
			idDoc.setTax_id_file_name(taxFile.getOriginalFilename());
			idDoc.setTax_id_file_type(taxFile.getContentType());
			
			try {
				idDoc.setAadhar_image(aadharFile.getBytes());
				idDoc.setTax_id_copy(taxFile.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<String>("Failed to upload files", HttpStatus.BAD_REQUEST);
			}
			idDoc.setUser_cred(existUserCredentail);
			existUserCredentail.setIdDoc(idDoc);
			idDocRepo.save(idDoc);
			return new ResponseEntity<String>("created identity doc", HttpStatus.OK);
		} 
		
		return new ResponseEntity<String>("Expired Session", HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/isAdded")
	public boolean statusDocument(@RequestParam("session") String sessionToken) {
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		if(currentUserSession != null) {
			UserCredential existUserCredential = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			IdentityDocument existIdDoc = existUserCredential.getIdDoc();
			if(existIdDoc != null)
				return true;
			else
				return false;
		} else 
			return false; 
	}
}
