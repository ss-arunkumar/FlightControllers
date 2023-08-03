package com.flight_info_sys.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flight_info_sys.Repository.CityRepo;
import com.flight_info_sys.entities.City;

@RestController
public class CityController {

	@Autowired
	CityRepo cityrepo;

//	@GetMapping("/city/getall")
//	public List<City> getallcities() {
//		return cr.findAll();
//
//	}

	
	//To retrieve the names in city
	@GetMapping("/city/names")
	public List<String> getcities() {
		List<String> city = new ArrayList<String>();
		// cr.findAll();
		for (var v : cityrepo.findAll()) {
			city.add(v.getName());
		}
		return city;
	}

	//To add a new Row into a City table
	@PostMapping("/city/add")
	@PreAuthorize("hasRole('ADMIN')")
	public City addnewCity(@RequestBody City city) {

		cityrepo.save(city);
		return city;

	}

	
	//To delete the row in a city by code
	@DeleteMapping("/city/delete/{city}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteCity(@PathVariable("city") String city) {
		cityrepo.deleteById(city);
		return "Deleted by " + city;

	}   
	  
	
	//To update the name in city
	@PutMapping("/city/update/{city}")
	@PreAuthorize("hasRole('ADMIN')")
	public City updatecity(@PathVariable("city") String city, @RequestParam("name") String name) {
		
		var c=cityrepo.findById(city).get();
		c.setName(name);
		cityrepo.save(c);
		return c;
		
	}
}
