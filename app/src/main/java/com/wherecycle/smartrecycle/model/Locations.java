package com.wherecycle.smartrecycle.model;

/**
 * Created by Claudine on 08/12/2017.
 */

public class Locations {
    private double lat;
    private double lng;
    private String email;

    public Locations(){

    }

    public Locations(double lat, double lng, String email){
        this.lat = lat;
        this.lng = lng;
        this.email = email;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng){
        this.lng = lng;
    }

    public void setAddress(String address){
        this.email = email;
    }

    public double getLat(){
        return lat;
    }

    public double getLng(){
        return lng;
    }

    public String getAddress(){
        return email;
    }
}


