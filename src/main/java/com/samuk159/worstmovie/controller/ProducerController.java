package com.samuk159.worstmovie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samuk159.worstmovie.dto.PrizeIntervalDTO;
import com.samuk159.worstmovie.model.service.ProducerService;

@RestController
@RequestMapping("/producers")
public class ProducerController {
	
	@Autowired
	private ProducerService service;

	@GetMapping("/prize-intervals")
	public PrizeIntervalDTO getMinAndMaxPrizeIntervals() {
		return service.getMinAndMaxPrizeIntervals();
	}
	
}
