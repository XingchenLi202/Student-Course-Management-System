package net.javaguides.springboot.model;

import java.util.*;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "students")

/*
 * firstName
 * lastName
 * studentId
 */
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long studentId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, 
				cascade = CascadeType.ALL, 
				mappedBy = "enrolledStudents")
	private Set<Course> courses = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(
			name = "student_timeslot_relationship",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "timeslot_id")
			)
	private Set<TimeSlot> timeslotsOfStudent = new HashSet<>();
	
	
	
	
	
	
	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
	 this.lastName = lastName;
	}

	
	
	public Set<Course> getCourses(){
		return courses;
	}
	
	
	
	
	public Set<TimeSlot> getTimeslotsOfStudent(){
		return timeslotsOfStudent;
	}
	
	public void setTimeslotsOfStudent(TimeSlot timeslot) {
		timeslotsOfStudent.add(timeslot);
	}
}