package com.resolariot.demo;

import javax.validation.constraints.NotNull;

public class VoltageRequest {

	@NotNull
	private Boolean status;

	@NotNull
	private String weather;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	@Override
	public String toString() {
		return this.getWeather() + ": " + this.getStatus();
	}

}
