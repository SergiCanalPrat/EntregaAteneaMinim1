package edu.upc.dsa.services;

import edu.upc.dsa.controladores.BikeManager;
import edu.upc.dsa.controladores.BikeManagerImpl;
import edu.upc.dsa.modelo.Bike;
import edu.upc.dsa.modelo.Station;
import edu.upc.dsa.modelo.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Path;

@Api(value = "/bikes", description = "Endpoint to Bike Service")
@Path("/bikes")
public class BikeService {

    private BikeManager bm;


    public BikeService(){
        this.bm = BikeManagerImpl.getInstance();


        //Si el catalogo está vacio
        if(bm.numUsers()==0 && bm.numStations()==0{
            this.bm.addBike("BIK1","bici verde",23,"STA1");
            this.bm.addBike("BIK2","bici azul",2.5,"STA2");
            this.bm.addBike("BIK3","bici roja",9.3,"STA3");


            this.bm.addUser( "A1",  "sergi",  "canal"));
            List<Bike> bikes = new ArrayList<>();
            bikes.add(this.bm.getBike("STA1", "US1"));
            bikes.add(this.bm.getBike("STA2","US2"));
            bikes.add(this.bm.getBike("STA3", "US3"));

            Station station = new Station( "STA1",  "STAD1",  50,  43.09,  890.90);
            Bike bike1 = new Bike("BIK1","bici verde",23,"STA1";
            Bike bike2 = new Bike("BIK2","bici azul",2.5,"STA2";
            Bike bike3 = new Bike("BIK3","bici roja",9.3,"STA3";

            this.bm.getUsers().get(1).addBike(bike1);
            this.bm.getUsers().get(2).addBike(bike2);
            this.bm.getUsers().get(3).addBike(bike3);


        }
    }


    //Get una bike
    @GET
    @ApiOperation(value = "get una bike", notes = "Get de una bike")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{bikeId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBike(@PathParam("bikeId") String stationId, String userId) {
        Bike b = this.bm.getBike(stationId,userId);
        if (b == null) return Response.status(404).build();
        else  return Response.status(201).entity(b).build();
    }

    //Get Bikes de un user
    @GET
    @ApiOperation(value = "get bikes de un user", notes = "Get bikes de un user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Bike.class),
            @ApiResponse(code = 404, message = "Track not found")
    })
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBikesByUser(@PathParam("userId") String userId) {
        User u = this.bm.getUsers().get(userId);
        List<Bike> listBikes= u.getBikesFromUser();
        if (u == null) return Response.status(404).build();
        else  return Response.status(201).entity(listBikes).build();
    }




}