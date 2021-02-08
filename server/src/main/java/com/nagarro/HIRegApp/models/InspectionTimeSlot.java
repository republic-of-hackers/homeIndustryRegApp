package com.nagarro.HIRegApp.models;

import java.io.Serializable;
import java.sql.Time;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="inspection_time_slot")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InspectionTimeSlot implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Time slot1;
	private Time slot2;
	private Time slot3;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserCredential slot1BookBy;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserCredential slot2BookBy;
	
	@OneToOne(cascade = CascadeType.ALL)
	private UserCredential slot3BookBy;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private InspectionInformation inspInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getSlot1() {
		return slot1;
	}

	public void setSlot1(Time slot1) {
		this.slot1 = slot1;
	}

	public Time getSlot2() {
		return slot2;
	}

	public void setSlot2(Time slot2) {
		this.slot2 = slot2;
	}

	public Time getSlot3() {
		return slot3;
	}

	public void setSlot3(Time slot3) {
		this.slot3 = slot3;
	}

	

	public UserCredential getSlot1BookBy() {
		return slot1BookBy;
	}

	public void setSlot1BookBy(UserCredential slot1BookBy) {
		this.slot1BookBy = slot1BookBy;
	}

	public UserCredential getSlot2BookBy() {
		return slot2BookBy;
	}

	public void setSlot2BookBy(UserCredential slot2BookBy) {
		this.slot2BookBy = slot2BookBy;
	}

	public UserCredential getSlot3BookBy() {
		return slot3BookBy;
	}

	public void setSlot3BookBy(UserCredential slot3BookBy) {
		this.slot3BookBy = slot3BookBy;
	}

	public InspectionInformation getInspInfo() {
		return inspInfo;
	}

	public void setInspInfo(InspectionInformation inspInfo) {
		this.inspInfo = inspInfo;
	}
	
	
}
