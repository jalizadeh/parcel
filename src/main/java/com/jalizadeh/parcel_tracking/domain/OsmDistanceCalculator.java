package com.jalizadeh.parcel_tracking.domain;

import org.springframework.stereotype.Component;

import com.jalizadeh.parcel_tracking.interfaces.DistanceCalculator;
import com.jalizadeh.parcel_tracking.interfaces.OsmDistanceClient;
import com.jalizadeh.parcel_tracking.model.Location;


@Component
public class OsmDistanceCalculator implements DistanceCalculator{
	
	private OsmDistanceClient osmDistanceClient;

	@Override
	public Double calculateDistanceInMeters(Location source, Location destination) {
		return osmDistanceClient.calculateDistanceInMeters(source, destination);
	}

	@Override
	public DistanceCalculationMethod calculationMethod() {
		return DistanceCalculationMethod.OSM_DISTANCE;
	}

}
