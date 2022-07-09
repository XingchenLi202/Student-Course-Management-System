package net.javaguides.springboot.service;

import net.javaguides.springboot.model.Location;

import java.util.*;

public interface LocationService {
	Location saveLocation(Location location);
	List<Location> getAllLocations();
	Location getLocationById(long id);
	Location updateLocation(Location location, long id);
	void deleteLocation(long id);
}
