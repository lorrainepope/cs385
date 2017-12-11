package com.wherecycle.smartrecycle.model;

/**
 * Created by Claudine on 08/12/2017.
 */

public class Locations {
    private String name;        //Edited by James 2017-12-11
    private double lat;
    private double lng;
    private String email;

    public Locations(){

    }

    public Locations(String name, double lat, double lng, String email){        //Edited by James 2017-12-11
        this.name = name;                       //Edited by James 2017-12-11
        this.lat = lat;
        this.lng = lng;
        this.email = email;
    }

    public void setName(String nam) {   this.name = nam;    }   //Edited by James 2017-12-11
    public String getName()   {   return name;    }             //Edited by James 2017-12-11

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


