package com.flight_info_sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight_info_sys.Repository.FlightHistoryRepo;
import com.flight_info_sys.entities.FlightsHistory;

@RestController
public class FlightsHistoryController {

	@Autowired
	FlightHistoryRepo flighthistoryrepo; 
	

//	@GetMapping("/flightshistory/getall")
//	public List<FlightsHistory> getfh() {
//		return fhr.findAll();
//
//	}

	
	// To get the Flight-History by flight-no (here the flight-no is in Composite primary Key)
	@GetMapping("/flighthistory/Byflightno/{fno}")
	public List<FlightsHistory> getflightsHistoryByid(@PathVariable("fno") String fno) {
		return flighthistoryrepo.findByFlightkeyfh_Flightno(fno);

	}

//  To retrieve the delayed flights by minutes
	@GetMapping("/flighthistory/delayed")
	public List<FlightsHistory> delayedHistory(@RequestParam("minute") int minute) {

		return flighthistoryrepo.delayedFlights(minute);

	}
}
