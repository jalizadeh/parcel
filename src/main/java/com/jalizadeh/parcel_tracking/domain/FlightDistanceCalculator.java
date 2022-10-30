package com.jalizadeh.parcel_tracking.domain;

import org.springframework.stereotype.Component;

import com.jalizadeh.parcel_tracking.interfaces.DistanceCalculator;
import com.jalizadeh.parcel_tracking.model.Location;

@Component
public class FlightDistanceCalculator implements DistanceCalculator {

	@Override
	public Double calculateDistanceInMeters(Location source, Location destination) {
		final int R = 6371000; //in meters
		
		Double lat1 = source.getLat();
		Double lng1 = source.getLng();
		Double lat2 = destination.getLat();
		Double lng2 = destination.getLng();
		
		Double latDistance = toRad(lat2 - lat1);
		Double lonDistance = toRad(lng2 - lng1);
		Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
				+ Math.cos(toRad(lat1)) * Math.cos(toRad(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		Double distance = R * c;
		
		return distance;
	}

	private static Double toRad(Double value) {
		return value * Math.PI / 180;
	}

	@Override
	public DistanceCalculationMethod calculationMethod() {
		return DistanceCalculationMethod.FLIGHT_DISTANCE;
	}

}
