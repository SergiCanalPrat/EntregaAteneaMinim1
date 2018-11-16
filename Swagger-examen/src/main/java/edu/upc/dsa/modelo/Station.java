package edu.upc.dsa.modelo;

import java.util.ArrayList;
import java.util.List;

public class Station {

    private String idStation;
    private int max;
    private String description;
    private double lat;
    private double lon;

    private ArrayList<Bike> bikes;

    public String getIdStation() {
        return idStation;
    }

    public Station(String idStation, String description, int max, double lat, double lon) {
        this.idStation = idStation;
        this.description = description;
        this.max = max;
        this.lat = lat;
        this.lon = lon;
    }

    public ArrayList<Bike> getBikes() {
        return bikes;
    }

}
