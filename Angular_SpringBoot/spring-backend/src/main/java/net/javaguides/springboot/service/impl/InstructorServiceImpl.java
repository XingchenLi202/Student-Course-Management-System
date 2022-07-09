package net.javaguides.springboot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Instructor;
import net.javaguides.springboot.repository.InstructorRepository;
import net.javaguides.springboot.service.InstructorService;

@Service
public class InstructorServiceImpl implements InstructorService{
	
	@Resource
	private InstructorRepository instructorRepository;
	
	public InstructorServiceImpl(InstructorRepository instructorRepository) {
		super();
		this.instructorRepository = instructorRepository;
	}
	
	@Override
	public Instructor saveInstructor(Instructor instructor) {
		return instructorRepository.save(instructor);
	}

	@Override
	public List<Instructor> getAllInstructors() {
		return instructorRepository.findAll();
	}

	@Override
	public Instructor getInstructorById(long id) {
		return instructorRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Instructor", "Id", id));
	}

	@Override
	public Instructor updateInstructor(Instructor instructor, long id) {
		
		// we need to check whether the instructor with given id is exist in DB or not
		Instructor existingInstructor = instructorRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("Instructor", "Id", id));
				
		existingInstructor.setFirstName(instructor.getFirstName());
		existingInstructor.setLastName(instructor.getLastName());
				
		//save existing instructor to DB
		instructorRepository.save(existingInstructor);
		return existingInstructor;
	}

	@Override
	public void deleteInstructor(long id) {
		
		// check whether a instructor exist in a DB or not
		instructorRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Instructor", "Id", id));
		instructorRepository.deleteById(id);
	}

}
