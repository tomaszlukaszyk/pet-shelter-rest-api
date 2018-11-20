package com.codecool.petshelter.endpoints;

import com.codecool.petshelter.dao.DogDao;
import com.codecool.petshelter.model.Pet;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/dogs")
public class DogEndpoint {

    private DogDao dao;

    @Inject
    public DogEndpoint(DogDao dao) {
        this.dao = dao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDogs() {
        System.out.println("endpoint");
        List<Pet> dogs = dao.getAllDogs();
        return Response.ok(dogs).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addDog(Pet pet) {
        long id = dao.addDog(pet);
        if (id < 0) return Response.status(500).entity("Dog already exists").build();
        return Response.ok("Dog added with id = " + id).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateDog(Pet pet) {
        if (dao.updateDog(pet)) {
            return Response.ok("Dog updated").build();
        }
        return Response.status(404).entity("Dog you are trying to change dose not exist in database").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDogById(@PathParam("id") Long id) {
        Pet dog = dao.getDogById(id);
        if (dog == null) return Response.status(404).entity("There is no dog in database with id = " + id).build();
        return Response.ok(dog).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteDogById(@PathParam("id") Long id) {
        if (dao.removeDog(id)){
            return Response.ok("Deleted successfully").build();
        }
        return Response.status(500).entity("Could not delete dog").build();
    }
}
