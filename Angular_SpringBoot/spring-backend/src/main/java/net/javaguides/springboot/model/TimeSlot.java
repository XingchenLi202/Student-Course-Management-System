package net.javaguides.springboot.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "time_slots")

/*
 * timeSlotId
 * startTime
 * endTime
 * weekDay
 */

public class TimeSlot {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long timeslotid;
	
	@Column(name = "day_of_week")
	@Enumerated(EnumType.STRING)
	private WeekDay dayofWeekDay;
	
	@Column(name = "start_time")
	@Enumerated(EnumType.STRING)
	private Time startTime;

	@Column(name = "end_time")
	@Enumerated(EnumType.STRING)
	private Time endTime;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, 
				cascade = CascadeType.ALL, 
				mappedBy = "timeslotsOfCourse")
	private Set<Course> courses = new HashSet<>();
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, 
				cascade = CascadeType.ALL, 
				mappedBy = "timeslotsOfStudent")
	private Set<Student> students = new HashSet<>();
	
	
	
	
	
	
	public long getTimeSlotId() {
		return timeslotid;
	}
	public void setTimeSlotId(long timeslotid) {
		this.timeslotid = timeslotid;
	}
	
	public WeekDay getWeekDay() {
		return dayofWeekDay;
	}
	
	public void setWeekDay(WeekDay dayofWeekDay) {
		this.dayofWeekDay = dayofWeekDay;
	}
	
	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	
	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	
	
	
	
	public Set<Course> getCourses(){
		return courses;
	}
	
	public Set<Student> getStudent(){
		return students;
	}
	
	
}
