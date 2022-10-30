package com.jalizadeh.parcel_tracking.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jalizadeh.parcel_tracking.client.GrassHopperResponse;
import com.jalizadeh.parcel_tracking.client.Path;



@RestController
public class GraphHopper {
	
	@GetMapping("/api/graphhopper/distance")
	public GrassHopperResponse calculateDistance(String source, String destination) {
		
		List<Path> paths = new ArrayList<Path>();
		paths.add(new Path(3215.2));
		
		return new GrassHopperResponse(paths);
	}
}
