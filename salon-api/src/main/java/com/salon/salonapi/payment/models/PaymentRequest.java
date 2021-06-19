package com.salon.salonapi.payment.models;

import lombok.Data;

//import javax.validation.constraints.Email;

@Data
public class PaymentRequest {

	private Long slotId;
	private Long selectedServiceId;
	
	private String firstName;
	private String lastName;
	

	private String email;
	private String phoneNumber;
}
