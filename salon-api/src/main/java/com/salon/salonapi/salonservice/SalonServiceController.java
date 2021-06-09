package com.salon.salonapi.salonservice;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/services")
public class SalonServiceController {

	private final SalonServiceDetailRepository salonServiceDetailRepository;

	public SalonServiceController(SalonServiceDetailRepository salonServiceDetailRepository) {
		this.salonServiceDetailRepository = salonServiceDetailRepository;
	}

	@GetMapping("/retrieveAvailableSalonServices")
	@ApiOperation(value = "RetrieveAvailableSalonServicesAPI")
	public List<SalonServiceDetail> retrieveAvailableSalonServicesAPI() {		
		return salonServiceDetailRepository.findAll();
	}
}
