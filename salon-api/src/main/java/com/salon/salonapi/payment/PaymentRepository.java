package com.salon.salonapi.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.salon.salonapi.payment.models.Payment;
import com.salon.salonapi.slot.Slot;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	Payment findBySlotAndEmail(Slot slot, String email);
}
