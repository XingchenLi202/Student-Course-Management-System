package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Course;

import java.util.*;

public interface CourseService {
	Course saveCourse(Course course);
	List<Course> getAllCourses();
	Course getCourseById(long id);
	Course updateCourse(Course course, long id);
	void deleteCourse(long id);
}
