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
import net.javaguides.springboot.model.Instructor;
import net.javaguides.springboot.service.InstructorService;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
	
	@Autowired
	private InstructorService instructorService;
	
	public InstructorController(InstructorService instructorService) {
		super();
		this.instructorService = instructorService;
	}
	
	
	// build create instructor REST API
	@PostMapping()
	public ResponseEntity<Instructor> saveInstructor(@RequestBody Instructor instructor){
		return new ResponseEntity<Instructor>(instructorService.saveInstructor(instructor), HttpStatus.CREATED);
	}
		
	// build get all instructors REST API
	@GetMapping
	public List<Instructor> getAllInstructor(){
		return instructorService.getAllInstructors();
	}
		
	// build get instructor by id REST API
	// http://localhost:8080/api/instructors/1
	@GetMapping("{id}")
	public ResponseEntity<Instructor> getInstructorById(@PathVariable("id") long instructorId) {
		return new ResponseEntity<Instructor>(instructorService.getInstructorById(instructorId), HttpStatus.OK);
	}
		
		
	// build update instructor REST API
	// http://localhost:8080/api/instructors/1
	@PutMapping("{id}")
	public ResponseEntity<Instructor> updateInstructor(@PathVariable("id") long id
													,@RequestBody Instructor instructor){
		return new ResponseEntity<Instructor>(instructorService.updateInstructor(instructor, id), HttpStatus.OK);
	}
		
	// build delete instructor REST API
	// http://localhost:8080/api/instructors/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteInstructor(@PathVariable("id") long id) {
			
		// delete instructor from DB
		instructorService.deleteInstructor(id);
			
		return new ResponseEntity<String>("Instructor delete successfully!.", HttpStatus.OK);
	}
}
