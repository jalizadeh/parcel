package com.jalizadeh.parcel_tracking.repositories;

import java.util.List;
import java.util.Optional;

import com.jalizadeh.parcel_tracking.model.Location;
import com.jalizadeh.parcel_tracking.model.Parcel;

public interface ParcelRepository{
	Parcel save(Parcel parcel);
	List<Parcel> findAll();
	Optional<Parcel> findById(Long id);
	void updateLocation(Long id, Location location);
	void delete(Long id);
}
