package com.salon.salonapi.ticket;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.salon.salonapi.payment.models.Payment;
import com.salon.salonapi.salonservice.SalonService;

@Service
public class TicketService {
	
	TicketRepository ticketRepository;
	SalonService salonService;	
	
	public TicketService(TicketRepository ticketRepository, SalonService salonService) {
		this.ticketRepository = ticketRepository;
		this.salonService = salonService;
	}

	@Transactional
	public Ticket book(Payment payment) {
		Ticket ticket = new Ticket();
		ticket.setPayment(payment);
		
		return ticketRepository.save(ticket);
	}
	
	public Optional<Ticket> findById(Long id) {
		return ticketRepository.findById(id);
	}

}
