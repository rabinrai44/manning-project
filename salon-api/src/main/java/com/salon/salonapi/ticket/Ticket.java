package com.salon.salonapi.ticket;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.salon.salonapi.payment.models.Payment;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ticket {

	TicketStatus ticketStatus = TicketStatus.BOOKED;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne
	private Payment payment;
}
