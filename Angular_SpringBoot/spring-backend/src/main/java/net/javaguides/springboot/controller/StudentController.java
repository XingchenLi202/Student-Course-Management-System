package net.javaguides.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.hibernate.engine.query.spi.sql.NativeSQLQueryCollectionReturn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import net.javaguides.springboot.model.Student;
import net.javaguides.springboot.model.TimeSlot;
import net.javaguides.springboot.service.StudentService;
import net.javaguides.springboot.service.TimeslotService;

@RestController
//@Controller
@RequestMapping("/api/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private TimeslotService timeslotService;

	public StudentController(StudentService studentService,
							TimeslotService timeslotService) {
		super();
		this.studentService = studentService;
		this.timeslotService = timeslotService;
	}
	
	// build create student REST API
	@PostMapping()
	public ResponseEntity<Student> saveStudent(@RequestBody Student student){
		return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.CREATED);
	}
	
	// build get all students REST API
	@GetMapping
	public List<Student> getAllStudents(){
		return studentService.getAllStudents();
	}
	
	// build get student by id REST API
	// http://localhost:8080/api/students/1
	@GetMapping("{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable("id") long studentId) {
		return new ResponseEntity<Student>(studentService.getStudentById(studentId), HttpStatus.OK);
	}
	
	
	// build update student REST API
	// http://localhost:8080/api/students/1
	@PutMapping("{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id
												,@RequestBody Student student){
		return new ResponseEntity<Student>(studentService.updateStudent(student, id), HttpStatus.OK);
	}
	
	// build delete student REST API
	// http://localhost:8080/api/students/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") long id) {
		
		// delete student from DB
		studentService.deleteStudent(id);
		
		return new ResponseEntity<String>("Student delete successfully!.", HttpStatus.OK);
	}
	
	
	
	
	@PutMapping("/{studentId}/havetimeslot/{timeslotId}")
	public ResponseEntity<Student> timeslotToStudent(@PathVariable long studentId,
								@PathVariable long timeslotId){
		
		Student student = studentService.getStudentById(studentId);
		TimeSlot timeslot = timeslotService.getTimeslotById(timeslotId);
		student.setTimeslotsOfStudent(timeslot);
		
		return new ResponseEntity<Student>(studentService.saveStudent(student), HttpStatus.OK);
	}
	
	
	
	
	
	
//	@GetMapping("/studentlist")
//	public String listStudents(Model model) {
////		List<Student> students = new ArrayList<>();
////		students.addAll(studentService.getAllStudents());
//		model.addAttribute("studentlist", studentService.getAllStudents());//studentService.getAllStudents()
//		return "studentlist";
//	}
//	@GetMapping("/showNewStudent")
//	public String showNewStudent(Model model) {
//		Student student  = new Student();
//		model.addAttribute("student",student);
//		return "create_student";
//	}
//	
//	
//	@PostMapping("/saveStudent")
//	public String saveStudent(@ModelAttribute("student") Student student) {
//		
//		studentService.saveStudent(student);
//		return "redirect:/studentlist";
//	}
//	@GetMapping("/showForUpdate/{id}")
//	public String showForUpdate(@PathVariable(value = "id") long id, Model model) {
//		Student student = studentService.getStudentById(id);
////		Student newstudent = studentService.updateStudent(student, id);
//		model.addAttribute("student",student);
//		return "update_student";
//	}
//	@GetMapping("/deleteStudent/{id}")
//	public String deleteStudent(@PathVariable(value="id") long id) {
//		this.studentService.deleteStudent(id);
//		return "redirect:/studentlist";
//	}
}
