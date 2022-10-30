package com.jalizadeh.parcel_tracking.interfaces;

import com.jalizadeh.parcel_tracking.model.Location;

public interface OsmDistanceClient{
	Double calculateDistanceInMeters(Location source, Location destination);
}
