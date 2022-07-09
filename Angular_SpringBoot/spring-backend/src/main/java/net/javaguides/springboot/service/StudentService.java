package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Student;

import java.util.*;

public interface StudentService {
	Student saveStudent(Student student);
	List<Student> getAllStudents();
	Student getStudentById(long id);
	Student updateStudent(Student student, long id);
	void deleteStudent(long id);
}
