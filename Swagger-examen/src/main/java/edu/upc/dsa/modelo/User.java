package edu.upc.dsa.modelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class User {
    public String getIdUser() {
        return idUser;
    }

    private String idUser;
    private String name;
    private String surname;
    private LinkedList<Bike> bikesFromUser;

    //Si hacemos un servicio siempre añadir el constructor vacio!!!!
    public User(){}

    public User(String idUser, String name, String surname) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.bikesFromUser = bikesFromUser;
    }

    public LinkedList<Bike> getBikesFromUser() {
        return bikesFromUser;
    }
    public void addBike(Bike b){
        this.getBikesFromUser().add(b);
    }
}
