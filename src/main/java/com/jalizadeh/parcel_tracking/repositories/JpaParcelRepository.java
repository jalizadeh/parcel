package com.jalizadeh.parcel_tracking.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.jalizadeh.parcel_tracking.entity.ParcelEntity;

public interface JpaParcelRepository extends JpaRepository<ParcelEntity, Long>{

	@Modifying
	@Query("update ParcelEntity p set p.locationLat=:locationLat, p.locationLng=:locationLng where p.id=:id")
	void updateLocation(Long id, Double locationLat, Double locationLng);

}
