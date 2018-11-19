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
import java.util.List;
import javax.ws.rs.Path;

@Api(value = "/bikes", description = "Endpoint to Bike Service")
@Path("/bikes")
public class BikeService {

    private BikeManager bm;


    public BikeService() {
        this.bm = BikeManagerImpl.getInstance();
    }

    //Get una bike
    @GET
    @ApiOperation(value = "Get de una bike", notes = "Get de una bike pasando StationId y UserId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Bike.class),
            @ApiResponse(code = 404, message = "Station not found"),
            @ApiResponse(code = 405, message = "User not found")
    })
    @Path("/{stationId}/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBike(@PathParam("stationId") String stationId,
                            @PathParam("userId") String userId) {
        try {
            Bike b = this.bm.getBike(stationId,userId);
            if (b == null)
                return Response.status(404).build();
            else
                return Response.status(200).entity(b).build();
        } catch (UserNotFoundException e) {
            return Response.status(405).build();
        } catch (StationNotFoundException e) {
            return Response.status(404).build();
        }

    }
}