package com.weather.apiwind.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.weather.apiwind.model.Weather;
import com.weather.apiwind.model.Wwind;

@Component
public class WindImp implements Wind {

	@Override
	public Wwind getWind(Long zipcode) {
		try {
			// get api data
			String url = "http://api.openweathermap.org/data/2.5/weather?q=" + zipcode
					+ ",us&APPID=83a729c979ae8debcba8761a29b20b19";
			RestTemplate restTemplate = new RestTemplate();
			Weather weather = restTemplate.getForObject(url, Weather.class);
			return weather.getWind();
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invaiid Zip Code:" + zipcode);
		}
	}

}
