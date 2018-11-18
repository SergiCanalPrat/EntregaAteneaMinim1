package edu.upc.dsa.services;

import edu.upc.dsa.controladores.BikeManagerImpl;
import edu.upc.dsa.exceptions.StationFullException;
import edu.upc.dsa.exceptions.StationNotFoundException;
import edu.upc.dsa.exceptions.UserNotFoundException;
import edu.upc.dsa.modelo.Bike;
import edu.upc.dsa.modelo.Station;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jdk.jfr.ContentType;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Api(value = "/stations", description = "Endpoint to Product Service")
@Path("/stations")
public class StationService {
    private BikeManagerImpl bm;


    public StationService() {
        this.bm = BikeManagerImpl.getInstance();
    }

    //Añade una estacion
    @POST
    @ApiOperation(value = "Post estacion", notes = "Añade una estacion a la lista de estaciones")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response= Station.class),
    })
    @Path("/")
    @Consumes(APPLICATION_JSON)
    public Response newStation(Station station) {
        this.bm.addStation(station.getIdStation(), station.getDescription(), station.getMax(), station.getLat(), station.getLon());
        return Response.status(201).entity(this.bm.getStationById(station.getIdStation())).build();
    }

    //Get todas las Stations
    @GET
    @ApiOperation(value = "Get stations", notes = "Responde una lista con todas las estaciones")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Station.class)
    })
    @Path("/")
    @Produces(APPLICATION_JSON)
    public Response getStations() {
        ArrayList<Station> stations = this.bm.getStations();
        GenericEntity<List<Station>> entity = new GenericEntity<List<Station>>(stations){};
        return Response.status(201).entity(entity).build();
    }

    //Get todas las bikes de una estacion ordenadas por kms
    @GET
    @ApiOperation(value = "GetBikesByStationOrderByKms", notes = "Responde con todas las bikes de una estacion ordenadas por kms")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Station.class),
            @ApiResponse(code = 404, message = "Station not found")
    })
    @Path("/{stationId}/bikes")
    @Produces(APPLICATION_JSON)
    public Response getbikesByStationOrderByKms(@PathParam("stationId") String stationId) throws StationNotFoundException {
        List<Bike> bikes = this.bm.bikesByStationOrderByKms(stationId);
        GenericEntity<List<Bike>> entity = new GenericEntity<List<Bike>>(bikes){};
        return Response.status(201).entity(entity).build();
    }
}