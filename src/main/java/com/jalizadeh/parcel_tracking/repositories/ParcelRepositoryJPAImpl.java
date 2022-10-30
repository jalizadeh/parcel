package com.jalizadeh.parcel_tracking.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jalizadeh.parcel_tracking.domain.DistanceCalculationMethod;
import com.jalizadeh.parcel_tracking.domain.DistanceCalculatorFactory;
import com.jalizadeh.parcel_tracking.entity.ParcelEntity;
import com.jalizadeh.parcel_tracking.model.Location;
import com.jalizadeh.parcel_tracking.model.Parcel;

@Repository
public class ParcelRepositoryJPAImpl implements ParcelRepository{

	@Autowired
	private JpaParcelRepository repository;
	
	@Autowired
	private DistanceCalculatorFactory dcFactory;
	
	
	@Override
	public Parcel save(Parcel parcel) {
		ParcelEntity saved = null;
		try {
			saved = repository.save(toParcelEntity(parcel));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toParcel(saved);
	}

	@Override
	public List<Parcel> findAll() {
		List<Parcel> all = new ArrayList<Parcel>();
		repository.findAll().stream().map(i -> all.add(toParcel(i))).collect(Collectors.toList());
		return all;
	}

	@Override
	public Optional<Parcel> findById(Long id) {
		return Optional.ofNullable(toParcel(repository.findById(id).orElseGet(null)));
	}

	@Transactional
	@Override
	public void updateLocation(Long id, Location location) {
		repository.updateLocation(id, location.getLat(), location.getLng());
		
	}

	
	@Transactional
	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	
	
	private ParcelEntity toParcelEntity(Parcel p) throws Exception {
		return new ParcelEntity(Long.valueOf(0), 
				p.getName(), 
				p.getLocation().getLat(), 
				p.getLocation().getLng(), 
				p.getDestination().getLat(), 
				p.getDestination().getLng(), 
				p.getDistanceToDestination(),
				DistanceCalculationMethod.FLIGHT_DISTANCE
			);
	}
	
	private Parcel toParcel(ParcelEntity p) {
		if(p == null)
			return null;
		
		return new Parcel(
				p.getId(),
				p.getName(),
				new Location(p.getLocationLat(), p.getLocationLng()),
				new Location(p.getDestinationLat(), p.getDestinationLng()),
				p.getDistanceToDestination()
				);
	}
	

}
