package edu.upc.dsa.services;

import edu.upc.dsa.controladores.BikeManager;
import edu.upc.dsa.controladores.BikeManagerImpl;
import edu.upc.dsa.exceptions.StationFullException;
import edu.upc.dsa.exceptions.StationNotFoundException;
import edu.upc.dsa.exceptions.UserNotFoundException;
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
import java.util.LinkedList;
import java.util.List;

@Api(value = "/users", description = "Endpoint to Bike Service")
@Path("/users")
public class UserService {

    private BikeManager bm;


    public UserService() {
        this.bm = BikeManagerImpl.getInstance();
    }

    //Añadir un user
    @POST
    @ApiOperation(value = "Post user", notes = "Añade un user al mapa de users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response=User.class),
    })

    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newUser(User user) {
        this.bm.getUsers().put(user.getIdUser(), user);
        return Response.status(201).entity(user).build();
    }

    //Get de todas las bikes de un user
    @GET
    @ApiOperation(value = "GetBikesByUser", notes = "Responde con todas las bikes de usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Station.class),
            @ApiResponse(code = 404, message = "Station not found")
    })
    @Path("/{userId}/bikes")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBikesByUser(@PathParam("userId") String userId) {
        try {
            List<Bike> bikes = this.bm.bikesByUser(userId);
            GenericEntity<List<Bike>> entity = new GenericEntity<List<Bike>>(bikes){};
            return Response.status(201).entity(entity).build();
        } catch (UserNotFoundException e) {
            return Response.status(404).build();
        }
    }

}