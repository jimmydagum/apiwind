package com.weather.apiwind.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Wclouds {

	Integer all;

	public Wclouds() {
		super();
	}

	public Wclouds(Integer all) {
		this.all = all;
	}

	public Integer getAll() {
		return all;
	}

	public void setAll(Integer all) {
		this.all = all;
	}

	@Override
	public String toString() {
		return "Wclouds [all=" + all + "]";
	}

}
