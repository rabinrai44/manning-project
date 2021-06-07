package com.salon.salonapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Configuration
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalonDetail {

	@Value("${salon.name}")
	private String name;
	
	@Value("${salon.address}")
	private String address;
	
	@Value("${salon.city}")
	private String city;
	
	@Value("${salon.state}")
	private String state;
	
	@Value("${salon.zipcode}")
	private String zipcode;
	
	@Value("${salon.phone}")
	private String phone;
	
	public SalonDetail SalonDetail() {
		SalonDetail salonDetail = new SalonDetail();
		
		salonDetail.address=address;
		salonDetail.city=city;
		salonDetail.state=state;
		salonDetail.zipcode=zipcode;
		salonDetail.phone=phone;
		salonDetail.name=name;
		
		return salonDetail;
	}
}
