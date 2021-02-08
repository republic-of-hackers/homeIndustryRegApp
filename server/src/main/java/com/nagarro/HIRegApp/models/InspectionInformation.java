package com.nagarro.HIRegApp.models;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="inspection_info")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InspectionInformation implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	// candidate key: city + date
	private String city;
	private Date availableDate;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<InspectionTimeSlot> timeSlots;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Date getAvailableDate() {
		return availableDate;
	}
	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}
	public List<InspectionTimeSlot> getTimeSlots() {
		return timeSlots;
	}
	public void setTimeSlots(List<InspectionTimeSlot> timeSlots) {
		this.timeSlots = timeSlots;
	}
	
}
