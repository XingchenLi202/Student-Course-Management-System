package net.javaguides.springboot.service.impl;

import java.util.*;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.TimeSlot;
import net.javaguides.springboot.repository.TimeslotRepository;
import net.javaguides.springboot.service.TimeslotService;

@Service
public class TimeslotServiceImpl implements TimeslotService{
	
	@Resource
	private TimeslotRepository timeslotRepository;
	
	public TimeslotServiceImpl(TimeslotRepository timeslotRepository) {
		super();
		this.timeslotRepository = timeslotRepository;
	}

	@Override
	public TimeSlot saveTimeslot(TimeSlot timeslot) {
		return timeslotRepository.save(timeslot);
	}

	@Override
	public List<TimeSlot> getAllTimeslots() {
		return timeslotRepository.findAll();
	}

	@Override
	public TimeSlot getTimeslotById(long id) {
		return timeslotRepository.findById(id).orElseThrow(() ->
					new ResourceNotFoundException("TimeSlot", "Id", id));
	}

	@Override
	public TimeSlot updateTimeslot(TimeSlot timeslot, long id) {
		// we need to check whether the time_slot with given id is exist in DB or not
		TimeSlot existingTimeSlot = timeslotRepository.findById(id).orElseThrow(
					() -> new ResourceNotFoundException("TimeSlot", "Id", id));
		
		existingTimeSlot.setWeekDay(timeslot.getWeekDay());
		existingTimeSlot.setStartTime(timeslot.getStartTime());
		existingTimeSlot.setEndTime(timeslot.getEndTime());
		
		//save existing time_slot to DB
		timeslotRepository.save(existingTimeSlot);
		return existingTimeSlot;
	}

	@Override
	public void deleteTimeslot(long id) {
		
		//check whether a time_slot exist in a DB or not
		timeslotRepository.findById(id).orElseThrow(() ->
				new ResourceNotFoundException("TimeSlot", "Id", id));
		timeslotRepository.deleteById(id);
	}
	
	
}
