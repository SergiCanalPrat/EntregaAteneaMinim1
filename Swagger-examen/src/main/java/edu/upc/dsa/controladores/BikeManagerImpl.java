package edu.upc.dsa.controladores;


import edu.upc.dsa.exceptions.StationFullException;
import edu.upc.dsa.exceptions.StationNotFoundException;
import edu.upc.dsa.exceptions.UserNotFoundException;
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
    private int numStations = S;

    public BikeManagerImpl() {
        this.users = new HashMap<>();
        this.stations = new ArrayList<>();
        this.bikes = new LinkedList<>();
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
    public void addBike(String idBike, String description, double kms, String idStation) throws StationFullException, StationNotFoundException{
        Station station = this.getStationById(idStation);
        if (station != null) {
            if (station.getMax() > this.numBikes(idStation)) {
                Bike bike = new Bike(idBike, description, kms, idStation);
                this.getStationById(idStation).addBike(bike);
            }
            else
                throw new StationFullException();
        }
        else
            throw new StationNotFoundException();

    }

    public List<Bike> bikesByStationOrderByKms(String idStation) throws StationNotFoundException {

        Station station = this.getStationById(idStation);
        if (station != null) {
            ArrayList<Bike> bikesList = station.getBikes();
            logger.info("bikesByStationOrderByKms: ordenando las bikes por kms...");
            logger.info("Lista sin ordenar: " + bikesList.toString());

            //Ordena de menor a Mayor.
            bikesList.sort(Comparator.comparingDouble(Bike::getKms));

            logger.info("Lista ordenada: " + bikesList.toString());
            return bikesList;
        }
        else
            throw new StationNotFoundException();
    }

    public Bike getBike(String stationId, String userId) throws UserNotFoundException, StationNotFoundException{
        //Buscamos la estacion, añadimos una bike al user y borramos la 1a bike de la estacion

        Station station = this.getStationById(stationId);
        if (station != null && !station.getBikes().isEmpty()) {
            User user = this.users.get(userId);
            if (user != null) {
                Bike bike = station.getBikes().remove(0);
                user.addBike(bike);
                return bike;
            } else {
                throw new UserNotFoundException();
            }
        } else
            throw new StationNotFoundException();

        /*
        logger.info("getBike: buscando la estacion...");
        Station station = this.getStationById(stationId);
        Bike bike;
        if (station != null && !station.getBikes().isEmpty()) {
            logger.info("getBike: buscando la bike...");
            bike = station.getBikes().get(0);
        }
        else
            throw new StationNotFoundException();
        User user = this.users.get(userId);
        if (user != null){
            logger.info("getBike: añadiendo bike de la estacion al usuario...");
            user.addBike(bike);
        }
        else
            throw new UserNotFoundException();
        return bike;*/
    }



    public LinkedList<Bike> bikesByUser(String userId) throws UserNotFoundException{
        User user = this.users.get(userId);
        if (user != null)
            return user.getBikesFromUser();
        else
            throw new UserNotFoundException();
    }

    public int numUsers() {
        return this.users.size();
    }

    public int numStations() {
        return this.stations.size();
    }

    public int numBikes(String idStation) throws StationNotFoundException {
        Station station = this.getStationById(idStation);
        if (station != null)
            return station.getBikes().size();
        else
            throw new StationNotFoundException();
    }


    public Station getStationById(String stationId) {

        for (int i = 0; i < stations.size(); i++) {
            Station station = stations.get(i);
            if (station.getIdStation().equals(stationId))
                return station;
        }

        return null;
    }

    public ArrayList<Station> getStations(){
        return this.stations;
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
