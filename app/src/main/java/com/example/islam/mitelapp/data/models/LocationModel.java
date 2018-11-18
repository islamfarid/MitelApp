package com.example.islam.mitelapp.data.models;

import com.google.gson.annotations.SerializedName;

public class LocationModel {
    @SerializedName("country")
    private String country;

    @SerializedName("name")
    private String name;

    @SerializedName("lon")
    private double lon;

    @SerializedName("id")
    private int id;

    @SerializedName("region")
    private String region;

    @SerializedName("lat")
    private double lat;

    @SerializedName("url")
    private String url;
    private String mKEywordSearch;

    public String getmKEywordSearch() {
        return mKEywordSearch;
    }

    public void setmKEywordSearch(String mKEywordSearch) {
        this.mKEywordSearch = mKEywordSearch;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public String getCountry(){
        return country;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLon(double lon){
        this.lon = lon;
    }

    public double getLon(){
        return lon;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setRegion(String region){
        this.region = region;
    }

    public String getRegion(){
        return region;
    }

    public void setLat(double lat){
        this.lat = lat;
    }

    public double getLat(){
        return lat;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl(){
        return url;
    }

    @Override
    public String toString(){
        return
                "SearchingLocationsResponseModel{" +
                        "country = '" + country + '\'' +
                        ",name = '" + name + '\'' +
                        ",lon = '" + lon + '\'' +
                        ",id = '" + id + '\'' +
                        ",region = '" + region + '\'' +
                        ",lat = '" + lat + '\'' +
                        ",url = '" + url + '\'' +
                        "}";
    }
}
