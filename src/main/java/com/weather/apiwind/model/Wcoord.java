package com.weather.apiwind.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wcoord {

	Double lon;
	Double lat;

	public Wcoord() {
		super();
	}

	public Wcoord(Double lon, Double lat) {
		this.lon = lon;
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	@Override
	public String toString() {
		return "Wcoord [lon=" + lon + ", lat=" + lat + "]";
	}

}
