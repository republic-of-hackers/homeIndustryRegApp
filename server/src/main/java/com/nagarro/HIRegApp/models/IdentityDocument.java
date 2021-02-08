package com.nagarro.HIRegApp.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="identity_docs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class IdentityDocument implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String aadhar_file_name;
	private String aadhar_file_type;
	private String tax_id_file_name;
	private String tax_id_file_type;
	
	@Lob
	private byte[] aadhar_image;
	
	@Lob
	private byte[] tax_id_copy;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserCredential user_cred;
	
	public String getAadhar_file_name() {
		return aadhar_file_name;
	}
	public void setAadhar_file_name(String aadhar_file_name) {
		this.aadhar_file_name = aadhar_file_name;
	}
	public String getAadhar_file_type() {
		return aadhar_file_type;
	}
	public void setAadhar_file_type(String aadhar_file_type) {
		this.aadhar_file_type = aadhar_file_type;
	}
	public String getTax_id_file_name() {
		return tax_id_file_name;
	}
	public void setTax_id_file_name(String tax_id_file_name) {
		this.tax_id_file_name = tax_id_file_name;
	}
	public String getTax_id_file_type() {
		return tax_id_file_type;
	}
	public void setTax_id_file_type(String tax_id_file_type) {
		this.tax_id_file_type = tax_id_file_type;
	}
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
	public byte[] getAadhar_image() {
		return aadhar_image;
	}
	public void setAadhar_image(byte[] aadhar_image) {
		this.aadhar_image = aadhar_image;
	}
	public byte[] getTax_id_copy() {
		return tax_id_copy;
	}
	public void setTax_id_copy(byte[] tax_id_copy) {
		this.tax_id_copy = tax_id_copy;
	}
	
	
	

}
