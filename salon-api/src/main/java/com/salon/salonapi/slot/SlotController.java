package com.salon.salonapi.slot;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/slots")
@Api(value = "Slot", tags = {"Slot"})
public class SlotController {

	SlotService slotService;

	public SlotController(SlotService slotService) {
		this.slotService = slotService;
	}	
	
	@GetMapping("/retrieveAvailableSlots/{salonServiceId}/{formattedDate}")
	@ApiOperation(value = "RetrieveAvailableSlotsAPI")
	public List<Slot> retrieveAvailableSlotsAPI(
			@PathVariable Long salonServiceId, @ApiParam(value = "Date in yyyy-MM-dd format", required = true, defaultValue = "2021-06/11") 
			@PathVariable String formattedDate) {
		return slotService.getSlotsForServiceOnDate(salonServiceId, formattedDate);
	}
	
}
