package com.codecool.petshelter.endpoints;

import com.codecool.petshelter.dao.PetDao;
import com.codecool.petshelter.model.Pet;
import com.codecool.petshelter.model.PetType;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cats")
public class CatEndpoint {

    private PetDao dao;

    @Inject
    public CatEndpoint(PetDao dao) {
        this.dao = dao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCats() {
        List<Pet> cats = dao.getAllPetsByType(PetType.CAT);
        return Response.ok(cats).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCat(Pet pet) {

        if (pet.getType() != PetType.CAT) return Response.status(400).entity("That is not a cat").build();

        long id = dao.addPet(pet);
        if (id < 0) return Response.status(400).entity("Cat already exists").build();
        return Response.ok("Cat added with id = " + id).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCat(Pet pet) {
        if (dao.updatePet(pet)) {
            return Response.ok("Cat updated").build();
        }
        return Response.status(404).entity("Cat you are trying to change dose not exist in database").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCetById(@PathParam("id") Long id) {
        Pet cat = dao.getPetById(id, PetType.CAT);
        if (cat == null) return Response.status(404).entity("There is no cat in database with id = " + id).build();
        return Response.ok(cat).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteCatById(@PathParam("id") Long id) {
        if (dao.removePet(id, PetType.CAT)){
            return Response.ok("Deleted successfully").build();
        }
        return Response.status(400).entity("Could not delete cat").build();
    }
}
