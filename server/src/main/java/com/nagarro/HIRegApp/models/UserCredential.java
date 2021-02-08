package com.nagarro.HIRegApp.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="user_cred")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserCredential implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique = true)
	private String userEmail;
	
	private String userPass;
	private boolean enabled;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	private UserPersonal userPersonal;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	private OrganisationInformation orgInfo;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	private IdentityDocument idDoc;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	private AccountInformation accInfo;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	private ExperienceInformation expInfo;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	private InspectionInformation inspInfo;
	
	
	
	public ExperienceInformation getExpInfo() {
		return expInfo;
	}

	public void setExpInfo(ExperienceInformation expInfo) {
		this.expInfo = expInfo;
	}

	public InspectionInformation getInspInfo() {
		return inspInfo;
	}

	public void setInspInfo(InspectionInformation inspInfo) {
		this.inspInfo = inspInfo;
	}

	public OrganisationInformation getOrgInfo() {
		return orgInfo;
	}

	public void setOrgInfo(OrganisationInformation orgInfo) {
		this.orgInfo = orgInfo;
	}

	public IdentityDocument getIdDoc() {
		return idDoc;
	}

	public void setIdDoc(IdentityDocument idDoc) {
		this.idDoc = idDoc;
	}

	public AccountInformation getAccInfo() {
		return accInfo;
	}

	public void setAccInfo(AccountInformation accInfo) {
		this.accInfo = accInfo;
	}

	UserCredential(){
		this.enabled = false;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public UserPersonal getUserPersonal() {
		return userPersonal;
	}

	public void setUserPersonal(UserPersonal userPersonal) {
		this.userPersonal = userPersonal;
	}

	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
