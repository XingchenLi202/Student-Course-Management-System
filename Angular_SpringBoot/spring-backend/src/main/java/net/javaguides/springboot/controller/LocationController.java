package net.javaguides.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.hibernate.engine.query.spi.sql.NativeSQLQueryCollectionReturn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import net.javaguides.springboot.model.Location;
import net.javaguides.springboot.service.LocationService;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	
	public LocationController(LocationService locationService) {
		super();
		this.locationService = locationService;
	}
	
	// build create location REST API
	@PostMapping()
	public ResponseEntity<Location> saveLocation(@RequestBody Location location){
		return new ResponseEntity<Location>(locationService.saveLocation(location), HttpStatus.CREATED);
	}
	

	// build get all locations REST API
	@GetMapping
	public List<Location> getAlllocations(){
		return locationService.getAllLocations();
	}
	
	// build get location by id REST API
	// http://localhost:8080/api/locations/1
	@GetMapping("{id}")
	public ResponseEntity<Location> getLocationById(@PathVariable("id") long locationId) {
		return new ResponseEntity<Location>(locationService.getLocationById(locationId), HttpStatus.OK);
	}
	
	
	// build update location REST API
	// http://localhost:8080/api/locations/1
	@PutMapping("{id}")
	public ResponseEntity<Location> updateLocation(@PathVariable("id") long id
												,@RequestBody Location location){
		return new ResponseEntity<Location>(locationService.updateLocation(location, id), HttpStatus.OK);
	}
	
	// build delete location REST API
	// http://localhost:8080/api/locations/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteLocation(@PathVariable("id") long id) {
		
		// delete location from DB
		locationService.deleteLocation(id);
		
		return new ResponseEntity<String>("Location delete successfully!.", HttpStatus.OK);
	}
}
