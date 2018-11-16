package edu.upc.dsa.modelo;

public class Bike {

    String idBike;
    String description;
    double kms;
    String idStation;


    public double getKms() {
        return kms;
    }

    public Bike(String idBike, String description, double kms, String idStation) {
        this.idBike = idBike;
        this.description = description;
        this.kms = kms;
        this.idStation = idStation;
    }

}
