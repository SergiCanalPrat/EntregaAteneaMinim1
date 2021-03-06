package edu.upc.dsa.modelo;

public class Bike {

    public Bike() {

    }


    public String getIdBike() {
        return idBike;
    }

    private String idBike;
    private String description;
    private double kms;
    private String idStation;


    public double getKms() {
        return kms;
    }

    public Bike(String idBike, String description, double kms, String idStation) {
        this.idBike = idBike;
        this.description = description;
        this.kms = kms;
        this.idStation = idStation;
    }

    public void setIdBike(String idBike) {
        this.idBike = idBike;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKms(double kms) {
        this.kms = kms;
    }

    public String getIdStation() {
        return idStation;
    }

    public void setIdStation(String idStation) {
        this.idStation = idStation;
    }
}
