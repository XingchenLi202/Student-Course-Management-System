package net.javaguides.springboot.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Course;
import net.javaguides.springboot.repository.CourseRepository;
import net.javaguides.springboot.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{
	
	@Resource
	private CourseRepository courseRepository;
	
	public CourseServiceImpl(CourseRepository courseRepository) {
		super();
		this.courseRepository = courseRepository;
	}

	@Override
	public Course saveCourse(Course course) {
		return courseRepository.save(course);
	}

	@Override
	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	@Override
	public Course getCourseById(long id) {
		return courseRepository.findById(id).orElseThrow(() ->
					new ResourceNotFoundException("Course", "Id", id));
	}

	@Override
	public Course updateCourse(Course course, long id) {
		// we need to check whether the course with given id is exist in DB or not
		Course existingCourse = courseRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Course", "Id", id));
		
		existingCourse.setCourseName(course.getCourseName());
//		existingCourse.setTimeSlotId(course.getTimeSlotId());
		
		//save existing course to DB
		courseRepository.save(existingCourse);
		return existingCourse;
	}

	@Override
	public void deleteCourse(long id) {
		
		// check whether a course exist in a DB or not
		courseRepository.findById(id).orElseThrow(() ->
					new ResourceNotFoundException("Course", "Id", id));
		courseRepository.deleteById(id);
	}
		
}
