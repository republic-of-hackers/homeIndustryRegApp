package com.nagarro.HIRegApp.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.multipart.MultipartFile;

import com.nagarro.HIRegApp.models.Attachment;
import com.nagarro.HIRegApp.models.ExperienceInformation;
import com.nagarro.HIRegApp.models.UserCredential;
import com.nagarro.HIRegApp.models.UserSession;

import com.nagarro.HIRegApp.repositories.AttachmentRepo;
import com.nagarro.HIRegApp.repositories.ExperienceInformationRepo;
import com.nagarro.HIRegApp.repositories.UserCredentialRepo;
import com.nagarro.HIRegApp.repositories.UserSessionRepo;

import com.nagarro.HIRegApp.services.FileStorageService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/exp")
public class ExpRestController {
	
	@Autowired
	private ExperienceInformationRepo expInfoRepo;
	
	@Autowired
	private AttachmentRepo attachmentRepo;
	
	@Autowired
	private FileStorageService storageService;
	
	@Autowired
	private UserCredentialRepo userCredentialRepo;
	
	@Autowired
	private UserSessionRepo userSessionRepo;
	
	@PostMapping("/addExp")
	public ResponseEntity<String> 
			addExp(@RequestParam("session")String sessionToken, 
				   @RequestPart("files") MultipartFile[] files,
				   @RequestPart("content") String content)
	{
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		if(currentUserSession != null) {
			UserCredential existUserCredential = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			if(existUserCredential != null) {
				List<Attachment> attachmentList = new ArrayList<Attachment>();
				ExperienceInformation expInfo = new ExperienceInformation();
				expInfo.setUser_cred(existUserCredential);
				expInfo.setContent(content);
				Arrays.asList(files).stream().forEach(file -> {
					Attachment attachment = null;
					try {
						 attachment = storageService.save(file);
					} catch (Exception e) {
						e.printStackTrace();
						attachmentRepo.deleteById(attachment.getId());
					}
					attachment.setExpInfo(expInfo);
					attachmentList.add(attachment);
					attachmentRepo.save(attachment);
				});
				expInfo.setFiles(attachmentList);
				expInfoRepo.save(expInfo);
				existUserCredential.setExpInfo(expInfo);
				userCredentialRepo.save(existUserCredential);
				return new ResponseEntity<String>("created exp", HttpStatus.OK);	
			}
			return new ResponseEntity<String>("User doesn't exist", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>("Expired Session", HttpStatus.FORBIDDEN); 
	}
	
	@GetMapping("/isAdded")
	public boolean 
		statusExperience(
			@RequestParam("session") String sessionToken) 
	{
		UserSession currentUserSession = userSessionRepo.findBySessionToken(sessionToken);
		if(currentUserSession != null) {
			UserCredential existUserCredential = userCredentialRepo.findByuserEmail(currentUserSession.getUserEmail());
			ExperienceInformation existExpInfo = existUserCredential.getExpInfo(); 
			if(existExpInfo != null)
				return true;
			else
				return false;
		} else 
			return false; 
	}
	

}
