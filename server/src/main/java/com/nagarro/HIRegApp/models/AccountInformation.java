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
@Table(name="account_info")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AccountInformation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String account_no;
	private String ifsc_code;
	private String alt_account_no;
	private String alt_ifsc_code;
	private String nominee_name;
	private String nominee_relation;
	private String nominee_contact_no;
	private String nominee_email;
	private String nominee_address;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserCredential user_cred;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserCredential getUser_cred() {
		return user_cred;
	}
	public void setUser_cred(UserCredential user_cred) {
		this.user_cred = user_cred;
	}
	public String getAccount_no() {
		return account_no;
	}
	public void setAccount_no(String account_no) {
		this.account_no = account_no;
	}
	public String getIfsc_code() {
		return ifsc_code;
	}
	public void setIfsc_code(String ifsc_code) {
		this.ifsc_code = ifsc_code;
	}
	public String getAlt_account_no() {
		return alt_account_no;
	}
	public void setAlt_account_no(String alt_account_no) {
		this.alt_account_no = alt_account_no;
	}
	public String getAlt_ifsc_code() {
		return alt_ifsc_code;
	}
	public void setAlt_ifsc_code(String alt_ifsc_code) {
		this.alt_ifsc_code = alt_ifsc_code;
	}
	public String getNominee_name() {
		return nominee_name;
	}
	public void setNominee_name(String nominee_name) {
		this.nominee_name = nominee_name;
	}
	public String getNominee_relation() {
		return nominee_relation;
	}
	public void setNominee_relation(String nominee_relation) {
		this.nominee_relation = nominee_relation;
	}
	public String getNominee_contact_no() {
		return nominee_contact_no;
	}
	public void setNominee_contact_no(String nominee_contact_no) {
		this.nominee_contact_no = nominee_contact_no;
	}
	public String getNominee_email() {
		return nominee_email;
	}
	public void setNominee_email(String nominee_email) {
		this.nominee_email = nominee_email;
	}
	public String getNominee_address() {
		return nominee_address;
	}
	public void setNominee_address(String nominee_address) {
		this.nominee_address = nominee_address;
	}
	
	
	

}
