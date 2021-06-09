package com.salon.salonapi.salonservice;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/services")
public class SalonServiceDetailController {

	private final SalonServiceDetailRepository salonServiceDetailRepository;

	public SalonServiceDetailController(SalonServiceDetailRepository salonServiceDetailRepository) {
		this.salonServiceDetailRepository = salonServiceDetailRepository;
	}

	@GetMapping("/retrieveAvailableSalonServices")
	public List<SalonServiceDetail> retrieveAvailableSalonServicesAPI() {		
		return salonServiceDetailRepository.findAll();
	}
}
