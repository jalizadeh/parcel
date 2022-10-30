package com.jalizadeh.parcel_tracking.controllers;

import com.jalizadeh.parcel_tracking.domain.DistanceCalculationMethod;
import com.jalizadeh.parcel_tracking.model.Location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class Payloads { }


@AllArgsConstructor
@Getter
@Setter
class ParcelCreationRequestPayload{
	String name;
	Location location;
	Location destination;
	DistanceCalculationMethod distanceCalculationMethod;
}


@AllArgsConstructor
@Getter
@Setter
class ParcelOutputPayload{
	Long id;
	String name;
	Location location;
	Location destination;
	Double distanceToDestination;
}