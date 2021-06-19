package com.salon.salonapi.slot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.salon.salonapi.salonservice.SalonService;
import com.salon.salonapi.salonservice.SalonServiceDetail;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SlotService {

	private final SlotRepository slotRepository;
	private final SalonService salonService;

	public SlotService(SlotRepository slotRepository, SalonService salonService) {
		this.slotRepository = slotRepository;
		this.salonService = salonService;
	}
	
	public Optional<Slot> findAvailableSlotId(Long slotId) {
		return slotRepository.findByIdAndStatus(slotId, SlotStatus.AVAILABLE);
	}

	public Optional<Slot> findLockedSlotId(Long slotId) {
		return slotRepository.findByIdAndStatus(slotId, SlotStatus.LOCKED);
	}
	public List<Slot> getSlotsForServiceOnDate(Long slotServiceId, String formattedDate) {
		SalonServiceDetail salonServiceDetail = salonService.findById(slotServiceId)
				.orElseThrow(() -> new RuntimeException("Invalid services"));

		System.out.println(salonServiceDetail.getName());
		LocalDate localDate = getAsDate(formattedDate);

		LocalDateTime startDate = localDate.atTime(0, 1);
		LocalDateTime endDate = localDate.atTime(23, 59);
		log.info("Querying for " + slotServiceId + " From " + startDate + " to " + endDate);
		List<Slot> results = slotRepository
				.findAllBySlotForGreaterThanEqualAndSlotForLessThanEqualAndAvailableServicesContainingAndStatus(
						startDate, endDate, salonServiceDetail, SlotStatus.AVAILABLE);

		for (Slot item : results) {
			System.out.println(item.getStylistName());
		}

		log.info("returning  " + results.size() + " Slots");
		return results;
	}

	private LocalDate getAsDate(String formattedDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		return LocalDate.parse(formattedDate, formatter);
	}
	
	 public void setToConfirmed(Slot slot) {
	        slot.setStatus(SlotStatus.CONFIRMED);
	        slot.setConfirmedAt(LocalDateTime.now());
	        save(slot);
	    }
	
	public void setToAvailable(Slot slot) {
		slot.setStatus(SlotStatus.AVAILABLE);
		slot.setSelectedService(null);
		slot.setLockedAt(null);
		save(slot);
	}
	
	public void setToLockedWithService(Slot slot, SalonServiceDetail serviceDetail) {
		slot.setStatus(SlotStatus.LOCKED);
		slot.setLockedAt(LocalDateTime.now());
		slot.setSelectedService(serviceDetail);
		save(slot);
	}

	@Transactional
	private void save(Slot slot) {
		slotRepository.save(slot);		
	}

}
