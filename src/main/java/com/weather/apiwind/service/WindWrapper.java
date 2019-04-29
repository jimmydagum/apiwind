package com.weather.apiwind.service;

import com.weather.apiwind.model.Wwind;

public abstract class WindWrapper implements Wind {

	private Wind wind;

	public WindWrapper(Wind wind) {
		this.wind = wind;
	}

	@Override
	public Wwind getWind(Long zipcode) {

		return wind.getWind(zipcode);
	}

}
