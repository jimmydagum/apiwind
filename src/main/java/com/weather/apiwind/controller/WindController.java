package com.weather.apiwind.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.weather.apiwind.model.Weather;
import com.weather.apiwind.model.Wwind;
import com.weather.apiwind.service.WindService;

@RestController
@RequestMapping("/api/v1")
public class WindController {

	@Autowired
	WindService wind;

	@GetMapping("/wind/{zipcode}")
	public Wwind getWind(@PathVariable("zipcode") Long zipcode) {
		return wind.getWind(zipcode);
	}

	// $ curl -x http://localhost:8080/api/v1/wind/clearCache
	@GetMapping("/clearCache")
	public String clearCache() {
		return wind.clearCache();
	}

	// testing purposes only to get API weather data
	@GetMapping("/weather/{zipcode}")
	public Weather getWeather(@PathVariable(value = "zipcode") Long zipcode) {
		String url = "http://api.openweathermap.org/data/2.5/weather?q=" + zipcode
				+ ",us&APPID=83a729c979ae8debcba8761a29b20b19";
		RestTemplate restTemplate = new RestTemplate();
		Weather weather = restTemplate.getForObject(url, Weather.class);
		return weather;
	}
}
