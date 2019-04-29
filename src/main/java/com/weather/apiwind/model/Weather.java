package com.weather.apiwind.model;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

	Wcoord coord;
	Wweather[] weather;
	String base;
	Wmain main;
	Integer visibility;
	Wwind wind;
	Wclouds clouds;
	Double dt;
	Wsys sys;
	Double id;
	String name;
	Integer cod;

	public Weather() {
		super();
	}

	public Weather(Wcoord coord, Wweather[] weather, String base, Wmain main, Integer visibility, Wwind wind,
			Wclouds clouds, Double dt, Wsys sys, Double id, String name, Integer cod) {
		this.coord = coord;
		this.weather = weather;
		this.base = base;
		this.main = main;
		this.visibility = visibility;
		this.wind = wind;
		this.clouds = clouds;
		this.dt = dt;
		this.sys = sys;
		this.id = id;
		this.name = name;
		this.cod = cod;
	}

	public Wcoord getCoord() {
		return coord;
	}

	public void setCoord(Wcoord coord) {
		this.coord = coord;
	}

	public Wweather[] getWeather() {
		return weather;
	}

	public void setWeather(Wweather[] weather) {
		this.weather = weather;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public Wmain getMain() {
		return main;
	}

	public void setMain(Wmain main) {
		this.main = main;
	}

	public Integer getVisibility() {
		return visibility;
	}

	public void setVisibility(Integer visibility) {
		this.visibility = visibility;
	}

	public Wwind getWind() {
		return wind;
	}

	public void setWind(Wwind wind) {
		this.wind = wind;
	}

	public Wclouds getClouds() {
		return clouds;
	}

	public void setClouds(Wclouds clouds) {
		this.clouds = clouds;
	}

	public Double getDt() {
		return dt;
	}

	public void setDt(Double dt) {
		this.dt = dt;
	}

	public Wsys getSys() {
		return sys;
	}

	public void setSys(Wsys sys) {
		this.sys = sys;
	}

	public Double getId() {
		return id;
	}

	public void setId(Double id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	@Override
	public String toString() {
		return "Weather [coord=" + coord + ", weather=" + Arrays.toString(weather) + ", base=" + base + ", main=" + main
				+ ", visibility=" + visibility + ", wind=" + wind + ", clouds=" + clouds + ", dt=" + dt + ", sys=" + sys
				+ ", id=" + id + ", name=" + name + ", cod=" + cod + "]";
	}

}
