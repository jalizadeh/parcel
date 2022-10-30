package com.jalizadeh.parcel_tracking.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jalizadeh.parcel_tracking.domain.DistanceCalculationMethod;
import com.jalizadeh.parcel_tracking.interfaces.DistanceCalculator;
import com.jalizadeh.parcel_tracking.model.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "parcel")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParcelEntity {
	
	public ParcelEntity(Long valueOf, String name2, Double lat, Double lng, Double lat2, Double lng2,
			Double distanceToDestination2, DistanceCalculator create) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(name = "location_lat")
	private Double locationLat;
	@Column(name = "location_lng")
	private Double locationLng;
	
	@Column(name = "destination_lat")
	private Double destinationLat;
	@Column(name = "destination_lng")
	private Double destinationLng;
	
	@Column(name = "distance_to_destination")
	private Double distanceToDestination;

	@Column(name = "distance_calculation_method")
	@Enumerated(EnumType.STRING)
	private DistanceCalculationMethod distanceCalculationMethod;

}
