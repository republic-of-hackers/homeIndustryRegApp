package com.nagarro.HIRegApp.models;

import java.io.Serializable;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="exp_info")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ExperienceInformation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String content;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Attachment> files;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserCredential user_cred;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Attachment> getFiles() {
		return files;
	}
	public void setFiles(List<Attachment> files) {
		this.files = files;
	}
	public UserCredential getUser_cred() {
		return user_cred;
	}
	public void setUser_cred(UserCredential user_cred) {
		this.user_cred = user_cred;
	}
}
