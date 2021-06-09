package com.salon.salonapi.salonservice;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SalonService {

	SalonServiceDetailRepository salonServiceDetailRepository;

	public SalonService(SalonServiceDetailRepository salonServiceDetailRepository) {
		this.salonServiceDetailRepository = salonServiceDetailRepository;
	}
	
	
	public List<SalonServiceDetail> findAll() {
		return salonServiceDetailRepository.findAll();
	}
	
	
}
