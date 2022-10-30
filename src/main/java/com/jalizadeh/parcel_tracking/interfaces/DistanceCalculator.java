package com.jalizadeh.parcel_tracking.interfaces;

import com.jalizadeh.parcel_tracking.domain.DistanceCalculationMethod;
import com.jalizadeh.parcel_tracking.model.Location;

public interface DistanceCalculator {

	DistanceCalculationMethod calculationMethod();
	Double calculateDistanceInMeters(Location source, Location destination);
	
}
