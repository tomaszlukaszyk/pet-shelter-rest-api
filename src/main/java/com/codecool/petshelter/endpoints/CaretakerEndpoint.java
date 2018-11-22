package com.codecool.petshelter.endpoints;

import com.codecool.petshelter.dao.CaretakerDao;
import com.codecool.petshelter.dao.PetDao;
import com.codecool.petshelter.model.Caretaker;
import com.codecool.petshelter.model.Pet;
import com.codecool.petshelter.model.PetType;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/caretakers")
public class CaretakerEndpoint {

    private CaretakerDao dao;
    private PetDao petDao;

    @Inject
    public CaretakerEndpoint(CaretakerDao dao, PetDao petDao) {
        this.dao = dao;
        this.petDao = petDao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCaretakers() {
        List<Caretaker> caretakers = dao.getAllCaretakers();
        return Response.ok(caretakers).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCaretaker(Caretaker caretaker) {
        long id = dao.addCaretaker(caretaker);
        if (id < 0) return Response.status(400).entity("Caretaker already exists").build();
        return Response.ok("Caretaker added with id = " + id).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCaretaker(Caretaker caretaker) {
        if (dao.updateCaretaker(caretaker)) {
            return Response.ok("Caretaker updated").build();
        }
        return Response.status(404).entity("Caretaker you are trying to change dose not exist in database").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCaretakerById(@PathParam("id") long id) {
        Caretaker caretaker = dao.getCaretakerById(id);
        if (caretaker == null) return Response.status(404).entity("There is no caretaker in database with id = " + id).build();
        return Response.ok(caretaker).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCaretakerById(@PathParam("id") long id) {
        if (dao.removeCaretaker(id)) {
            return Response.ok("Deleted successfully").build();
        }
        return Response.status(400).entity("Could not delete caretaker").build();
    }

    @PUT
    @Path("{caretakerId}/{petType}/{petId}")
    public Response addPetToCaretaker(@PathParam("caretakerId") long caretakerId, @PathParam("petType") String petType, @PathParam("petId") long petId) {
        Caretaker caretaker = dao.getCaretakerById(caretakerId);
        if (caretaker == null) return Response.status(404).entity("There is no caretaker in database with id = " + caretakerId).build();

        Pet pet = petDao.getPetById(petId, PetType.valueOf(petType.toUpperCase()));
        if (pet == null) return Response.status(404).entity("There is no " + petType + " in database with id = " + petId).build();

        caretaker.addPet(pet);
        dao.updateCaretaker(caretaker);
        return Response.ok("Pet added to caretaker").build();
    }
}
