package com.entity;

public class Photo {
	private String src;
	 private String country;
	 private String name;
	 private String resolution;
	 private String latitude_longitude;
	 private String ac_time;
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getLatitude_longitude() {
		return latitude_longitude;
	}
	public void setLatitude_longitude(String latitude_longitude) {
		this.latitude_longitude = latitude_longitude;
	}
	public String getAc_time() {
		return ac_time;
	}
	public void setAc_time(String ac_time) {
		this.ac_time = ac_time;
	}
}
