package edu.upc.dsa.controladores;


import edu.upc.dsa.modelo.Bike;
import edu.upc.dsa.modelo.Station;
import edu.upc.dsa.modelo.User;
import org.apache.log4j.Logger;

import java.util.*;


public class BikeManagerImpl implements BikeManager {
    private static BikeManagerImpl instance = null;
    final static Logger logger = Logger.getLogger(BikeManagerImpl.class);
    private HashMap<String, User> users;
    private ArrayList<Station> stations;
    private LinkedList<Bike> bikes;


    public BikeManagerImpl(HashMap<String, User> users, ArrayList<Station> stations, LinkedList<Bike> bikes) {
        this.users = users;
        this.stations = stations;
        this.bikes = bikes;
    }



    //Patron Singleton
    public static BikeManagerImpl getInstance() {
        if (instance == null) instance = new BikeManagerImpl();
        return instance;
    }



    //Afegir un user
    public void addUser(String idUser, String name, String surname) {
        User user = new User(idUser,name,surname);
        this.users.put(idUser,user);
    }

    //Afegir una station
    public void addStation(String idStation, String description, int max, double lat, double lon) {
        Station station = new Station(idStation,description,max,lat,lon);
        this.stations.add(station);
    }

    //Afegir una bike
    public void addBike(String idBike, String description, double kms, String idStation) {
        Bike bike = new Bike(idBike,description,kms,idStation);
        this.bikes.add(bike);
    }

    public List<Bike> bikesByStationOrderByKms(String idStation) {
        logger.info("bikesByStationOrderByKms: ordenando las bikes por kms...");
        logger.info("Lista sin ordenar: "+this.bikes.toString());

        //Ordena de menor a Mayor.
        bikes.sort(Comparator.comparingDouble(Bike::getKms));

        logger.info("Lista ordenada: "+bikes);
        return bikes;
    }

    public Bike getBike(String stationId, String userId){
        //Buscamos la estacion, añadimos una bike al user y borramos la 1a bike de la estacion
        logger.info("getBike: buscando la bike...");
        Bike bike = this.getStationById(stationId).getBikes().get(0);
        logger.info("getBike: añadiendo bike de la estacion al usuario...");
        this.users.get(userId).addBike(bike);
        return bike;
    }



    public List<Bike> bikesByUser(String userId) {
        return this.users.get(userId).getBikesFromUser();
    }

    public int numUsers() {
        return this.users.size();
    }

    public int numStations() {
        return this.stations.size();
    }

    public int numBikes(String idStation)  {
        return this.getStationById(idStation).getBikes().size();
    }


    private Station getStationById(String stationId){
        logger.info("getBike: buscando estación...");
        boolean found = false;
        for(Station station : stations) {
        if(station.getIdStation().equals(stationId)) {
            found = true;
            return station;
        }
    }
        if(!found)
            logger.info("Station Not Found");
        return null;
    }

    public Map<String,User> getUsers(){

        return this.users;
    }


    public void clear() {
        users.clear();
        stations.clear();
        bikes.clear();
    }
}





}