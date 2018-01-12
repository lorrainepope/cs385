package com.wherecycle.smartrecycle.model;

/**
 * Created by Claudine on 08/12/2017.
 */

public class Locations {
    private String name;        //Edited by James 2017-12-11
    private double lat;
    private double lng;
    private String phone;
    private String email;
    private boolean alumin;
    private boolean batteries;
    private boolean cardboard;
    private boolean electronics;
    private boolean furniture;
    private boolean glass;
    private boolean metal;
    private boolean plastics;
    private boolean textiles;

    public Locations(){

    }

    public Locations(String name, double lat, double lng, String phone, String email, boolean a, boolean b, boolean c, boolean e, boolean f, boolean g, boolean m, boolean p, boolean t){        //Edited by James 2017-12-11
        this.name = name;                       //Edited by James 2017-12-11
        this.lat = lat;
        this.lng = lng;
        this.phone = phone;
        this.email = email;
        this.alumin = a;
        this.batteries = b;
        this.cardboard = c;
        this.electronics = e;
        this.furniture = f;
        this.glass = g;
        this.metal = m;
        this.plastics = p;
        this.textiles = t;
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

    public String getPhone() {return phone;}

    public String getEmail(){
        return email;
    }

    public boolean isAlumin() {
        return alumin;
    }

    public boolean isBatteries() {
        return batteries;
    }

    public boolean isCardboard() {
        return cardboard;
    }

    public boolean isElectronics() {
        return electronics;
    }

    public boolean isFurniture() {
        return furniture;
    }

    public boolean isGlass() {
        return glass;
    }

    public boolean isMetal() {
        return metal;
    }

    public boolean isPlastics() {
        return plastics;
    }

    public boolean isTextiles() {
        return textiles;
    }

}


