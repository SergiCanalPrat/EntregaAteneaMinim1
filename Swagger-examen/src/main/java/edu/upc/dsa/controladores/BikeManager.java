package edu.upc.dsa.controladores;

import edu.upc.dsa.exceptions.StationFullException;
import edu.upc.dsa.exceptions.StationNotFoundException;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.modelo.Bike;
import edu.upc.dsa.modelo.Station;
import edu.upc.dsa.modelo.User;

import java.util.List;
import java.util.Map;

public interface BikeManager {

        /**
         * number of stations
         */
        public static final int S = 10;

        /**
         * Add a new User
         *
         * @param idUser identifier of the user
         * @param name name of the user
         * @param surname surname of the user
         */
        public void addUser(String idUser, String name, String surname);


        /**
         * Add a new Station
         *
         * @param idStation identifier of the station
         * @param description description
         * @param max maximum number of bikes of the station
         * @param lat lattitude GPS
         * @param lon longitude GPS
         */
        public void addStation(String idStation, String description, int max, double lat, double lon);


        /**
         * Add a new Bike into a Station
         *
         * @param idBike identifier of the bike
         * @param description description
         * @param kms kilometers
         * @param idStation identifier of the station

         */
        public void addBike(String idBike, String description, double kms, String idStation) throws StationFullException, StationNotFoundException;


        /**
         * Get the bikes of a station ordered by kilometers
         *
         * @param idStation identifier
         * @return list of bikes

         */
        public List<Bike> bikesByStationOrderByKms(String idStation) throws StationNotFoundException;

        /**
         * get the first Bike of the station
         *
         * @param idStation identifier of the station
         * @param userId identifier of the user
         * @return the first bike of the station
        */
        public Bike getBike(String idStation, String userId) throws UserNotFoundException, StationNotFoundException;

        /**
         * get the bikes used by the user
         *
         * @param userId identifier of the user
         * @return the list of bikes used by the user
         */
        public List<Bike> bikesByUser(String userId) throws UserNotFoundException;


        /**
         * get the number of users
         *
         * @return the number of users
         */
        public int numUsers();

        /**
         * get the number of stations
         *
         * @return the number of stations
         */
        public int numStations();

        /**
         * get the number of bikes in a station
         *
         * @param idStation identifier of the station
         * @return the number of bikes of the station
         */
        public int numBikes(String idStation) throws StationNotFoundException;

        /**
         * clear all the data structures
         */
        public void clear();

        public Station getStationById(String stationId);
        public Map<String,User> getUsers();
 }

