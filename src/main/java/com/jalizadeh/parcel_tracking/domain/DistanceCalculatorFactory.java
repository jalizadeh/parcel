package com.jalizadeh.parcel_tracking.domain;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.jalizadeh.parcel_tracking.interfaces.DistanceCalculator;

@Component
public class DistanceCalculatorFactory {

	private List<DistanceCalculator> distanceCalculators = Arrays.asList(
				new FlightDistanceCalculator(),
				new OsmDistanceCalculator()
			);
	
	public DistanceCalculator create(DistanceCalculationMethod distanceCalculationMethod) throws Exception {
		for(DistanceCalculator dc : distanceCalculators) {
			if(dc.calculationMethod().equals(distanceCalculationMethod))
				return dc;
		}
		
		throw new Exception("The provided distance calculation method doesn't exist");
	}
	
}
