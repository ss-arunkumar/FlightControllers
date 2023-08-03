package com.flight_info_sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight_info_sys.Repository.FlightRepo;
import com.flight_info_sys.Repository.ScheduledRepo;
import com.flight_info_sys.entities.FlightKey;
import com.flight_info_sys.entities.ScheduledFlight;

@RestController
public class ScheduledFlightsController {
	@Autowired
	ScheduledRepo schedulerepo;
	@Autowired
	FlightRepo flightrep;

//	@GetMapping("/scheduled/getall")
//	public List<ScheduledFlight> getschf() {
//		return scr.findAll();
//
//	}

	// To add new Schedule to scheduledFlight with inputs flight-no, departure-date and arrival-date
	
	@PostMapping("/scheduled/addNewOnlyByfromAndTodate")
	public ScheduledFlight newscheduled(@RequestParam("fno") String fno, @RequestParam("fdate") String fdate,
			@RequestParam("tdate") String tdate) {

		var f = flightrep.findById(fno).get();
		FlightKey fk = new FlightKey();
		fk.setFlightno(fno);
		fk.setDepdate(fdate);
		ScheduledFlight sc1 = new ScheduledFlight();
		sc1.setArrdate_sf(tdate);
		sc1.setArrtime_sf(f.getArtime());
		sc1.setFromcity(f.getFromcity());
		sc1.setTocity(f.getTocity());
		sc1.setFlightkeysf(fk);
		sc1.setSf_duration(f.getDuration());
		sc1.setDeptime(f.getDeptime());

		schedulerepo.save(sc1);

		return sc1;

	}

	
	// To delete the flight history by date range
	@DeleteMapping("/scheduledflight/delete")
	@PreAuthorize("hasRole('ADMIN')")
	public int deleteBydates(@RequestParam("fdate") String fdate, @RequestParam("tdate") String tdate) {

		return schedulerepo.deleteByFlightkeysf_DepdateBetween(fdate, tdate);  //Derived Query

	}
	
	
	//To retrieve scheduled flights by departure-date and from-city 
	@GetMapping("/scheduledflight/byDateAndCity")
	public List<ScheduledFlight> getbydateandCity(@RequestParam("depdate") String depdate,@RequestParam("city") String city){
		
		//Derived Query
		return schedulerepo.findByFlightkeysf_DepdateAndFromcitysf_code(depdate, city); 
		
	}
}
