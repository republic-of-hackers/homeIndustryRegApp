package com.nagarro.HIRegApp.controllers;

import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.nagarro.HIRegApp.dto.Credential;
import com.nagarro.HIRegApp.models.UserCredential;
import com.nagarro.HIRegApp.models.UserSession;
import com.nagarro.HIRegApp.models.VerificationToken;

import com.nagarro.HIRegApp.repositories.UserCredentialRepo;
import com.nagarro.HIRegApp.repositories.UserSessionRepo;
import com.nagarro.HIRegApp.repositories.VerificationTokenRepo;

import com.nagarro.HIRegApp.services.EmailServiceImpl;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/users")
public class UserRestController {
	
	@Autowired
	private BCryptPasswordEncoder passEncoder;
	
	// Repository Wiring
	@Autowired
	private UserCredentialRepo userCredentialRepo;
	
	@Autowired
	private VerificationTokenRepo verificationTokenRepo;
	
	@Autowired
	private UserSessionRepo userSessionRepo;
	
	// Service Wiring
	@Autowired
	private EmailServiceImpl emailService;
	
	@PostMapping("/addUser")
	public ResponseEntity<String> addUser(@RequestBody UserCredential user) {
		if(user == null)
			return new ResponseEntity<String>("Can't get your credential.", HttpStatus.BAD_REQUEST);
		String user_email = user.getUserEmail();
		UserCredential existUser = userCredentialRepo.findByuserEmail(user_email);
		if(existUser != null) {
			if(existUser.isEnabled())
				return new ResponseEntity<String>("verified", HttpStatus.OK);
			else 
				return new ResponseEntity<String>("registered", HttpStatus.OK);
		} else {
			String pwd = user.getUserPass();
			user.setUserPass(passEncoder.encode(pwd));
			
			VerificationToken verificationToken = new VerificationToken(user);
			verificationTokenRepo.save(verificationToken);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
	        mailMessage.setTo(user.getUserEmail());
	        mailMessage.setSubject("Complete Registration!");
	        mailMessage.setFrom("realm4geek@gmail.com");
	        mailMessage.setText("To confirm your account, please click here : "
	        +"http://localhost:8080/api/v1/users/confirm-account?token="+verificationToken.getToken());
	         
	        emailService.sendEmail(mailMessage);
			userCredentialRepo.save(user);
	        return new ResponseEntity<String>("checkmail", HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
	public String confirmUser(@RequestParam("token")String confirmationToken) {
		VerificationToken token = verificationTokenRepo.findByToken(confirmationToken);
		if(token != null) {
			Optional<UserCredential> opt  = userCredentialRepo.findById(token.getUser_cred().getId());
			UserCredential user = opt.get();
			user.setEnabled(true);
			userCredentialRepo.save(user);
			return "Your mail is verified";
		} else {
			return "Your mail is not verified try later.";
		}
	}
	
	// btoa("te") - > dhsajkhdkjas
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody Credential cred) {
		byte[] e = Base64.getDecoder().decode(cred.getEmail());
		byte[] p = Base64.getDecoder().decode(cred.getPassword());
		String email = new String(e);
		String password = new String(p);
		
		UserCredential currentUser = userCredentialRepo.findByuserEmail(email);
		if(currentUser != null) {
			if(passEncoder.matches(password, currentUser.getUserPass())) {
				if(currentUser.isEnabled()) {
					UserSession existUserSession = userSessionRepo.findByUserEmail(currentUser.getUserEmail());
					String sessionToken = UUID.randomUUID().toString();
					if(existUserSession != null) {
						existUserSession.setSessionToken(sessionToken);
					} else {
						UserSession userSession = new UserSession();
						userSession.setUserEmail(currentUser.getUserEmail());
						userSession.setSessionToken(sessionToken);
						userSessionRepo.save(userSession);
					}
					return new ResponseEntity<String>(sessionToken, HttpStatus.OK);
				} else
					return new ResponseEntity<String>("Verify Your Account", HttpStatus.UNAUTHORIZED); 
			} else
				return new ResponseEntity<String>("Incorrect Password", HttpStatus.NOT_FOUND);
		} else
			return new ResponseEntity<String>("User Doesn't Exist", HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> logoutUser(@RequestParam("useremail")String userEmail){
		UserSession existUser = userSessionRepo.findByUserEmail(userEmail);
		userSessionRepo.delete(existUser);
		return new ResponseEntity<String>("", HttpStatus.OK);
	}
}
