package com.nagarro.HIRegApp.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="org_info")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class OrganisationInformation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String license_number;
	private String registered_name;
	private String ownership_details;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserCredential user_cred;
	
	public UserCredential getUser_cred() {
		return user_cred;
	}
	public void setUser_cred(UserCredential user_cred) {
		this.user_cred = user_cred;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLicense_number() {
		return license_number;
	}
	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}
	public String getRegistered_name() {
		return registered_name;
	}
	public void setRegistered_name(String registered_name) {
		this.registered_name = registered_name;
	}
	public String getOwnership_details() {
		return ownership_details;
	}
	public void setOwnership_details(String ownership_details) {
		this.ownership_details = ownership_details;
	}
	
	

}
