package com.salon.salonapi.payment.models;

import com.sun.istack.NotNull;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class PaymentRequest {

	private Long slotId;
	private Long selectedServiceId;

	@NotNull
	@Size(min = 1)
	private String firstName;
	
	@NotNull
	@Size(min = 1)
	private String lastName;
	

	@NotNull
	@Email(message = "Email should be valid")
	private String email;
	
	@NotNull
	@Size(min = 10)
	private String phoneNumber;
}
