package com.salon.salonapi.salonservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@ToString
public class SalonServiceDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	String name;
	String description;
	Long price;
	Integer timeInMinutes;

	public SalonServiceDetail() {
	}

	public SalonServiceDetail(Long id, String name, String description, Long price, Integer timeInMinutes) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.timeInMinutes = timeInMinutes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getTimeInMinutes() {
		return timeInMinutes;
	}

	public void setTimeInMinutes(Integer timeInMinutes) {
		this.timeInMinutes = timeInMinutes;
	}

}
