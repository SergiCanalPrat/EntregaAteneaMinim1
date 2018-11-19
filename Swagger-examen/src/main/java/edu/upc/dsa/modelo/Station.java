package edu.upc.dsa.modelo;

import java.util.ArrayList;
import java.util.List;

public class Station {

    public Station (){

    }


    private String idStation;
    private int max;
    private String description;
    private double lat;
    private double lon;


    private ArrayList<Bike> bikes;

    public String getIdStation() {
        return idStation;
    }

    public int getMax() {
        return max;
    }

    public Station(String idStation, String description, int max, double lat, double lon) {
        this.idStation = idStation;
        this.description = description;
        this.max = max;
        this.lat = lat;
        this.lon = lon;
        this.bikes = new ArrayList<>();
    }

    public ArrayList<Bike> getBikes() {
        return bikes;
    }

    public void addBike(Bike b){
        this.bikes.add(b);
    }

    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setBikes(ArrayList<Bike> bikes) {
        this.bikes = bikes;
    }
}
