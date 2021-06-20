package com.salon.salonapi.payment;

import com.salon.salonapi.common.SalonException;
import com.salon.salonapi.config.SalonDetails;
import com.salon.salonapi.payment.models.Payment;
import com.salon.salonapi.payment.models.PaymentConfirmationResponse;
import com.salon.salonapi.payment.models.PaymentRequest;
import com.salon.salonapi.ticket.Ticket;

import io.swagger.annotations.ApiOperation;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
	PaymentService paymentService;
	SalonDetails salonDetails;

	public PaymentController(PaymentService paymentService, SalonDetails salonDetails) {
		this.paymentService = paymentService;
		this.salonDetails = salonDetails;
	}

	@PostMapping("/initiate")
	@ApiOperation(value = "InitialPayment")
	public Payment initiatePayment(@Valid @RequestBody PaymentRequest paymentRequest) {
		try {
			return paymentService.initiate(paymentRequest);
		} catch (ConstraintViolationException e) {
			e.printStackTrace();
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		} catch (SalonException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}

	@PutMapping("/confirm/{id}")
	@ApiOperation(value = "VerifyPaymentAndConfirmSlot")
	public PaymentConfirmationResponse verifyPaymentAndConfirmSlot(@PathVariable Long id) {
		try {
			Ticket ticket = paymentService.confirm(id);

			PaymentConfirmationResponse paymentConfirmationResponse = new PaymentConfirmationResponse();
			paymentConfirmationResponse.setSalonDetails(salonDetails.clone());
			paymentConfirmationResponse.setTicket(ticket);
			return paymentConfirmationResponse;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
	}
}
