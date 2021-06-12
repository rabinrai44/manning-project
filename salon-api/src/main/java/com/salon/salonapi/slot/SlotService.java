package com.salon.salonapi.slot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.salon.salonapi.salonservice.SalonService;
import com.salon.salonapi.salonservice.SalonServiceDetail;

import lombok.AllArgsConstructor;
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

	public List<Slot> getSlotsForServiceOnDate(Long slotServiceId, String formattedDate) {		
		SalonServiceDetail salonServiceDetail = salonService.findById(slotServiceId).orElseThrow(() -> new RuntimeException("Invalid services"));
		
		System.out.println(salonServiceDetail.getName());
		LocalDate localDate = getAsDate(formattedDate);
		
		LocalDateTime startDate = localDate.atTime(0, 1);
		LocalDateTime endDate = localDate.atTime(23, 59);
		log.info("Querying for " + slotServiceId + " From " + startDate + " to " + endDate);
		List<Slot> results = slotRepository.findAllBySlotForGreaterThanEqualAndSlotForLessThanEqualAndAvailableServicesContainingAndStatus(startDate, endDate, salonServiceDetail, SlotStatus.AVAILABLE);
		
		for(Slot item : results) {
			System.out.println(item.getStylistName());
		}
		
		log.info("returning  " + results.size() + " Slots");
		return results;
	}

	private LocalDate getAsDate(String formattedDate) {		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		return LocalDate.parse(formattedDate, formatter);
	}
	

}
