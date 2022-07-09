package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Instructor;

import java.util.*;

public interface InstructorService {
	Instructor saveInstructor(Instructor instuctor);
	List<Instructor> getAllInstructors();
	Instructor getInstructorById(long id);
	Instructor updateInstructor(Instructor instructor, long id);
	void deleteInstructor(long id);
}
