package com.jalizadeh.parcel_tracking.client;

import org.springframework.web.reactive.function.client.WebClient;

import com.jalizadeh.parcel_tracking.interfaces.OsmDistanceClient;
import com.jalizadeh.parcel_tracking.model.Location;

public class SpringOsmDistanceClient implements OsmDistanceClient{

	@Override
	public Double calculateDistanceInMeters(Location source, Location destination) {
		WebClient webClient = WebClient.create("http://localhost:8080");
		GrassHopperResponse response = webClient.get()
			.uri("http://localhost:8080/api/graphhopper/distance?source="+source.getLat()+","+source.getLng()+"&destination="+destination.getLat()+","+destination.getLng())
			.retrieve()
			.bodyToMono(GrassHopperResponse.class)
			.block();
		
		return response.getPaths().get(0).distance;
	}

	
	
	
	public static void main(String[] args) {
		Location src = new Location(654.83, 654921.4);
		Location dst = new Location(2354.1,9876.2);

		SpringOsmDistanceClient springOsmDistanceClient = new SpringOsmDistanceClient();
		System.out.println(springOsmDistanceClient.calculateDistanceInMeters(src, dst));
	}
}



