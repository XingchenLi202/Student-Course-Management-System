package net.javaguides.springboot.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "courses")

/*
 * courseId
 * timeSlotId
 */
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long courseid;
	
	@Column(name = "course_name")
	private String courseName;
	
//	@Column(name = "time_slot")
//	private long timeslotid;
	
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "course_student_relationship",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name = "student_id")
			)
	private Set<Student> enrolledStudents = new HashSet<>(); //mappedBy = 
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "timeslot_course_relationship",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name = "timeslot_id")
			)
	private Set<TimeSlot> timeslotsOfCourse = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "instructor_course_relationship",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name = "instructor_id")
			)
	private Set<Instructor> instructorsOfCourse = new HashSet<>();
	
	public long getCourseId() {
		return courseid;
	}
	
	public void setCourseId(long courseid) {
		this.courseid = courseid;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
//	public long getTimeSlotId() {
//		return timeslotid;
//	}
//	
//	public void setTimeSlotId(long timeslotid) {
//		this.timeslotid = timeslotid;
//	}
	
	
	
	
	public Set<Student> getEnrolledStudents(){
		return enrolledStudents;
	}
	
	public void setEnrolledStudents(Student student) {
		enrolledStudents.add(student);
	}
	
	
	public Set<TimeSlot> getTimeslotsOfCourse(){
		return timeslotsOfCourse;
	}
	
	public void setTimeslotsOfCourse(TimeSlot timeslot) {
		timeslotsOfCourse.add(timeslot);
	}
	
	public Set<Instructor> getInstructorsOfCourse(){
		return instructorsOfCourse;
	}
	
	public void setInstructorsOfCourse(Instructor instructor) {
		instructorsOfCourse.add(instructor);
	}
}

