package com.example.islam.mitelapp.data.models;

import com.google.gson.annotations.SerializedName;

public class CurrentWeatherModel {

	@SerializedName("current")
	private Current current;

	@SerializedName("location")
	private LocationModel location;

	public void setCurrent(Current current){
		this.current = current;
	}

	public Current getCurrent(){
		return current;
	}

	public void setLocation(LocationModel location){
		this.location = location;
	}

	public LocationModel getLocation(){
		return location;
	}

	@Override
 	public String toString(){
		return 
			"CurrentWeatherModel{" +
			"current = '" + current + '\'' + 
			",location = '" + location + '\'' + 
			"}";
		}
}