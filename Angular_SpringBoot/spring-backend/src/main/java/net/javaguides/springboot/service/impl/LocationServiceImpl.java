package net.javaguides.springboot.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Location;
import net.javaguides.springboot.repository.LocationRepository;
import net.javaguides.springboot.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{
	
	@Resource
	private LocationRepository locationRepository;
	
	public LocationServiceImpl(LocationRepository locationRepository) {
		super();
		this.locationRepository = locationRepository;
	}
	
	@Override
	public Location saveLocation(Location location) {
		return locationRepository.save(location);
	}

	@Override
	public List<Location> getAllLocations() {
		return locationRepository.findAll();
	}

	@Override
	public Location getLocationById(long id) {
		return locationRepository.findById(id).orElseThrow(() -> 
				new ResourceNotFoundException("Location", "Id", id));

	}

	@Override
	public Location updateLocation(Location location, long id) {

		// we need to check whether location with given id is exist in DB or not
		Location existingLocation = locationRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Location", "Id", id));
		
		//save existing location to DB
		locationRepository.save(existingLocation);
		return existingLocation;
	}

	@Override
	public void deleteLocation(long id) {

		// check whether a location exist in a DB or not
		locationRepository.findById(id).orElseThrow(() -> 
						new ResourceNotFoundException("location", "Id", id));
		locationRepository.deleteById(id);
		
	}

}
