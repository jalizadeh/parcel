package com.jalizadeh.parcel_tracking.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jalizadeh.parcel_tracking.domain.DistanceCalculatorFactory;
import com.jalizadeh.parcel_tracking.model.Location;
import com.jalizadeh.parcel_tracking.model.Parcel;
import com.jalizadeh.parcel_tracking.repositories.ParcelRepository;

@RestController
public class ParcelController {

	@Autowired
	private ParcelRepository repository;
	
	@Autowired
	private DistanceCalculatorFactory distanceCalculatorFactory;

	@PostMapping("/api/parcels")
	public ParcelOutputPayload create(@RequestBody ParcelCreationRequestPayload payload) {
		Parcel createdParcel = null;
		try {
			createdParcel = repository
					.save(new Parcel(payload.name, 
							new Location(payload.location.getLat(), payload.location.getLng()),
							new Location(payload.destination.getLat(), payload.destination.getLng()), 
							distanceCalculatorFactory.create(payload.getDistanceCalculationMethod())));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapToPayload(createdParcel);
	}

	@GetMapping("/api/parcels")
	public List<ParcelOutputPayload> getAll() {
		return repository.findAll().stream().map(p -> mapToPayload(p)).collect(Collectors.toList());
	}

	@GetMapping("/api/parcels/{id}")
	public ResponseEntity<ParcelOutputPayload> getParcel(@PathVariable long id) {
		Parcel found = repository.findById(id).orElse(null);
		if (found == null)
			return ResponseEntity.notFound().build();

		return ResponseEntity.ok(mapToPayload(found));
	}

	@DeleteMapping("/api/parcels/{id}")
	public void delete(@PathVariable("id") long id) {
		repository.delete(id);
	}

	@PatchMapping("/api/parcels/{id}")
	public void updateLocation(@PathVariable("id") long id, @RequestBody Location location) {
		repository.updateLocation(id, new Location(location.getLat(), location.getLng()));
	}

	private ParcelOutputPayload mapToPayload(Parcel parcel) {
		return new ParcelOutputPayload(parcel.getId(), parcel.getName(), parcel.getLocation(), parcel.getDestination(),
				parcel.getDistanceToDestination());
	}
}
