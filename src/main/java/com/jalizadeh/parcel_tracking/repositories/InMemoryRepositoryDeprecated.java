package com.jalizadeh.parcel_tracking.repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.jalizadeh.parcel_tracking.model.Location;
import com.jalizadeh.parcel_tracking.model.Parcel;


//@Repository
public class InMemoryRepositoryDeprecated implements ParcelRepository{

	private Map<Long, Parcel> db = new HashMap<Long, Parcel>();
	private AtomicLong counter = new AtomicLong(0);
	
	
	@Override
	public Parcel save(Parcel parcel) {
		Parcel p = null;
		try {
			p = (Parcel) parcel.clone();
			p.setId(counter.incrementAndGet());
			db.put(p.getId(), p);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return p;
	}

	@Override
	public List<Parcel> findAll() {
		return new ArrayList<Parcel>(db.values());
	}

	@Override
	public Optional<Parcel> findById(Long id) {
		return Optional.ofNullable(db.get(id));
	}

	@Override
	public void updateLocation(Long id, Location location) {
		Parcel p = db.get(id);
		Location l = null;
		
		try {
			l = (Location) location.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		p.setLocation(l);
		System.out.println(p);
	}
	

	@Override
	public void delete(Long id) {
		 db.remove(id);
	}

}
