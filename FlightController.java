package com.flight_info_sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight_info_sys.Repository.FlightRepo;
import com.flight_info_sys.entities.Flight;

@RestController
public class FlightController {

	@Autowired
	FlightRepo flightrepo;

//	@GetMapping("/flight/getall")
//	public List<Flight> flightlist() {
//
//		return fr.findAll();
//
//	}
//	

	// To get the flight by pagination
	@GetMapping("/flight/page/{num}")
	public List<Flight> getflightBypage(@PathVariable("num") int num) {
		var p = flightrepo.findAll(PageRequest.of(num, 5));
		return p.getContent();

	}

	// To get the Flights by From-city and To-city
	@GetMapping("/flight/bycities")
	public List<Flight> getfligthbycity(@RequestParam("fcity") String fcity, @RequestParam("tcity") String tcity) {
		var fc = flightrepo.getFlightsbycity(fcity, tcity);
		return fc;

	}

	
	//To add new flight 
	@PostMapping("/flight/addnew")
	@PreAuthorize("hasRole('ADMIN')")
	public Flight newFlight(@RequestBody Flight flight) {
		flightrepo.save(flight);
		return flight;

	}

}
