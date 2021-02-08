package com.nagarro.HIRegApp.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.nagarro.HIRegApp.constants.EmailVerification;

@Entity
public class VerificationToken implements EmailVerification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String token;
	
	@OneToOne(targetEntity = UserCredential.class, fetch = FetchType.EAGER)
	private UserCredential user_cred;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	public VerificationToken() {}
	
	public VerificationToken(UserCredential user) {
		this.user_cred = user;
		this.createdDate = new Date();
		this.token = UUID.randomUUID().toString();
		
	}
	

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserCredential getUser_cred() {
		return user_cred;
	}

	public void setUser_cred(UserCredential user_cred) {
		this.user_cred = user_cred;
	}
}
