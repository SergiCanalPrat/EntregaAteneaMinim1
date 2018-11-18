package edu.upc.dsa.exceptions;

public class StationNotFoundException extends Exception {

    public StationNotFoundException() {
        super();
    }

    public StationNotFoundException(String message) {
        super(message);
    }
}