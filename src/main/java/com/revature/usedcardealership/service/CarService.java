package com.revature.usedcardealership.service;

import com.revature.usedcardealership.dao.CarRepoDB;
import com.revature.usedcardealership.models.Vehicle;
import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

@Path("/car")
public class CarService {
CarRepoDB carRepoDB = new CarRepoDB();

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() throws IOException {


        return Response.ok((ArrayList<Vehicle>)carRepoDB.getAllCars()).build();
    }

    @GET
    @Path("/transactions")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactions() throws IOException {

        return Response.ok((ArrayList<String>)carRepoDB.viewTransactions()).build();
    }

    @POST
    @Path("/newVehicle")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response addNewVehicle(@FormDataParam("id") int id, @FormDataParam("year") int year, @FormDataParam("make") String make, @FormDataParam("model") String model, @FormDataParam("price") int price) throws URISyntaxException {
//      System.out.println(id+year + make + model+price);
        CarRepoDB carRepoDB = new CarRepoDB();
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setYear(year);
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setForSale(price);
//        System.out.println(vehicle);
        carRepoDB.addVehicle(vehicle);

        return Response.status(401).build();

    }

}
