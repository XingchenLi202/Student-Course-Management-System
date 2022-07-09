package net.javaguides.springboot.model;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "locations")
public class Location {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long locationid;

	
	public long getLocationId() {
		return locationid;
	}

	public void setLocationId(long locationid) {
		this.locationid = locationid;
	}

}