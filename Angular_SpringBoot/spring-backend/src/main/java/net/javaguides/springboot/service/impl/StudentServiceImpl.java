package net.javaguides.springboot.service.impl;

//import java.lang.*;
import java.util.List;
//import java.util.Optional;
//import java.util.Optional;

import javax.annotation.Resource;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;

import net.javaguides.springboot.model.Student;
import net.javaguides.springboot.repository.StudentRepository;
import net.javaguides.springboot.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{

	@Resource
	private StudentRepository studentRepository;
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}


	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}
	
	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}


	@Override
	public Student getStudentById(long id) {
//		Optional<Student> optional = studentRepository.findById(id);
//		Student student = null;
//		if(optional.isPresent()) {
//			student = optional.get();
//		} else {
//			throw new RuntimeException("Student not find");
//		}
//		return student;
		
		return studentRepository.findById(id).orElseThrow(() -> 
							new ResourceNotFoundException("Student", "Id", id));
		
	}


	@Override
	public Student updateStudent(Student student, long id) {
		
		// we need to check whether student with given id is exist in DB or not
		Student existingStudent = studentRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Student", "Id", id));
		
		existingStudent.setFirstName(student.getFirstName());
		existingStudent.setLastName(student.getLastName());
		
		//save existing student to DB
		studentRepository.save(existingStudent);
		return existingStudent;
	}


	@Override
	public void deleteStudent(long id) {
		
		// check whether a student exist in a DB or not
		studentRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("Student", "Id", id));
		this.studentRepository.deleteById(id);
		
	}
	
}
