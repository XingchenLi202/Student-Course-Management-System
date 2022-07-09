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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "instructors")

public class Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long instructorid;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, 
				cascade = CascadeType.ALL, 
				mappedBy = "instructorsOfCourse")
	private Set<Course> courses = new HashSet<>();
	
	
	
	
	
	public long getInstructorId() {
		return instructorid;
	}

	public void setInstructorId(long instructorid) {
		this.instructorid = instructorid;
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
}
