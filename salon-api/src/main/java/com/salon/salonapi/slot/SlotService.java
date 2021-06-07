package com.salon.salonapi.slot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
public class SlotService {
	
	private final SlotRepository slotRepository;	
	
	public SlotService(SlotRepository slotRepository) {
		this.slotRepository = slotRepository;
	}


	public List<Slot> getAllSlots() {
		return slotRepository.findAll();
	}

}
