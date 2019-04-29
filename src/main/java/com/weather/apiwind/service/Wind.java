package com.weather.apiwind.service;

import com.weather.apiwind.model.Wwind;

public interface Wind {
	Wwind getWind(Long zipcode);
}
