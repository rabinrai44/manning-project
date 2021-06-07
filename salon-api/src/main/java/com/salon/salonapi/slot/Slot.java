package com.salon.salonapi.slot;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.salon.salonapi.salonservice.SalonServiceDetail;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Slot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(fetch = FetchType.EAGER)
	@JsonIgnore
	Set<SalonServiceDetail> availableServices;

	@ManyToOne
	private SalonServiceDetail selectedService;

	String stylistName;

	LocalDateTime slotFor;

	private SlotStatus status;

	LocalDateTime lockedAt;

	public Slot() {
	}

}

enum SlotStatus {
	AVAILABLE, LOCKED, CONFIRMED, CANCELLED
}
