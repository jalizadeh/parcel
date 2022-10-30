package com.jalizadeh.parcel_tracking.model;

import com.jalizadeh.parcel_tracking.interfaces.DistanceCalculator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Parcel implements Cloneable{
	
	Long id;
	String name;
	Location location;
	Location destination;
	Double distanceToDestination;

	public Parcel(String name, Location location, Location destination, DistanceCalculator distanceCalculator) {
		this.name = name;
		this.location = location;
		this.destination = destination;
		//this.distanceCalculator = distanceCalculator;
		this.distanceToDestination = distanceCalculator.calculateDistanceInMeters(this.location, this.destination);
	}
	
	public Parcel(Long id, String name, Location location, Location destination, DistanceCalculator distanceCalculator) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.destination = destination;
		//this.distanceCalculator = distanceCalculator;
		this.distanceToDestination = distanceCalculator.calculateDistanceInMeters(this.location, this.destination);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
}


