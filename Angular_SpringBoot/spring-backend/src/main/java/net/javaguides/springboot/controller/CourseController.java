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
import net.javaguides.springboot.model.*;
import net.javaguides.springboot.service.CourseService;
import net.javaguides.springboot.service.InstructorService;
import net.javaguides.springboot.service.StudentService;
import net.javaguides.springboot.service.TimeslotService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private TimeslotService timeslotService;
	@Autowired
	private InstructorService instructorService;
	
	
//	why can there be only one constructor
//	public CourseController(CourseService courseService) {
////		super();
//		this.courseService = courseService;
//	}
	
	public CourseController(CourseService courseService, 
							StudentService studentService,
							TimeslotService timeslotService,
							InstructorService instructorService
							) {
		super();
		this.courseService = courseService;
		this.studentService = studentService;
		this.timeslotService = timeslotService;
		this.instructorService = instructorService;
	}
	
	
	// build create course REST API
	@PostMapping()
	public ResponseEntity<Course> saveCourse(@RequestBody Course course){
		return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.CREATED);
	}
	
	// build get all course REST API
	@GetMapping
	public List<Course> getAllCourse(){
		return courseService.getAllCourses();
	}
	
	//build get course by id REST API
	//http://localhost:8080/api/courses/1
	@GetMapping("{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable("id") long courseId){
		return new ResponseEntity<Course>(courseService.getCourseById(courseId), HttpStatus.OK);
	}
	
	// build update course REST API
	// http://localhost:8080/api/courses/1
	@PutMapping("{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable("id") long id
											,@RequestBody Course course){
		return new ResponseEntity<Course>(courseService.updateCourse(course, id), HttpStatus.OK);
	}
	
	
	// build delete course REST API
	// http://localhost:8080/api/courses/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable("id") long id){
		
		//delete course from DB
		courseService.deleteCourse(id);
		
		return new ResponseEntity<String>("Course delete successfully!.", HttpStatus.OK);
	}
	
	
	
	@PutMapping("/{courseId}/enrollstudent/{studentId}")
	public ResponseEntity<Course> enrollStudentToCourse(@PathVariable long courseId,
								@PathVariable long studentId) {
		Course course = courseService.getCourseById(courseId);
		Student student = studentService.getStudentById(studentId);
		course.setEnrolledStudents(student);
		
		return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.OK);
	}
	
	@PutMapping("/{courseId}/havetimeslot/{timeslotId}")
	public ResponseEntity<Course> timeslotToCourse(@PathVariable long courseId,
								@PathVariable long timeslotId) {
		
		Course course = courseService.getCourseById(courseId);
		TimeSlot timeslot = timeslotService.getTimeslotById(timeslotId);
		course.setTimeslotsOfCourse(timeslot);
		
		return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.OK);
	}
	
	@PutMapping("/{courseId}/keyinstructor/{instructorId}")
	public ResponseEntity<Course> keyInstructorToCourse(@PathVariable long courseId,
						@PathVariable long instructorId){
		
		Course course = courseService.getCourseById(courseId);
		Instructor instructor = instructorService.getInstructorById(instructorId);
		course.setInstructorsOfCourse(instructor);
		return new ResponseEntity<Course>(courseService.saveCourse(course), HttpStatus.OK);
	}
}
