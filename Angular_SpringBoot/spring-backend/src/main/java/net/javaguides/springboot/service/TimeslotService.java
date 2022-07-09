package net.javaguides.springboot.service;

import net.javaguides.springboot.model.TimeSlot;

import java.util.List;

public interface TimeslotService {
	TimeSlot saveTimeslot(TimeSlot timeslot);
	List<TimeSlot> getAllTimeslots();
	TimeSlot getTimeslotById(long id);
	TimeSlot updateTimeslot(TimeSlot timeslot, long id);
	void deleteTimeslot(long id);
}
