package net.javaguides.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import net.javaguides.springboot.model.TimeSlot;
import net.javaguides.springboot.service.TimeslotService;

@RestController
@RequestMapping("/api/timeslots")
public class TimeslotController {
	
	
	@Autowired
	private TimeslotService timeslotService;
	
	public TimeslotController(TimeslotService timeslotService) {
		super();
		this.timeslotService = timeslotService;
	}

	// build create time_slot REST API
	@PostMapping()
	public ResponseEntity<TimeSlot> saveTimeslot(@RequestBody TimeSlot timeslot){
		return new ResponseEntity<TimeSlot>(timeslotService.saveTimeslot(timeslot), HttpStatus.CREATED);
	}
	
	// build get all time_slot REST API
	@GetMapping
	public List<TimeSlot> getAllTimeslots(){
		return timeslotService.getAllTimeslots();
	}
	
	// build get time_slot by id REST API
	// http://localhost:8080/api/timeslots/1
	@GetMapping("{id}")
	public ResponseEntity<TimeSlot> getTimeslotById(@PathVariable("id") long timeslotId){
		return new ResponseEntity<TimeSlot>(timeslotService.getTimeslotById(timeslotId), HttpStatus.OK);
	}
	
	// build update time_slot REST API
	// http://localhost:8080/api/timeslots/1
	@PutMapping("{id}")
	public ResponseEntity<TimeSlot> updateTimeslot(@PathVariable("id") long id,
													@RequestBody TimeSlot timeslot){
		return new ResponseEntity<TimeSlot>(timeslotService.updateTimeslot(timeslot, id), HttpStatus.OK);
	}
	
	// build delete time_slot REST API
	// http://localhost:8080/api/instructors/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTimeslot(@PathVariable("id") long id){
		
		// delete time_slot from DB
		timeslotService.deleteTimeslot(id);
		return new ResponseEntity<String>("Instructor delete successfully!.", HttpStatus.OK);
	}
}
