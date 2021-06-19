package com.salon.salonapi.payment.models;

import com.salon.salonapi.config.SalonDetails;
import com.salon.salonapi.ticket.Ticket;

import lombok.Data;

@Data
public class PaymentConfirmationResponse {
	Ticket ticket;
	SalonDetails salonDetails;
}
