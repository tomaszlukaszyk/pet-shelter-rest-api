package com.codecool.petshelter.endpoints;

import com.codecool.petshelter.dao.AdoptionDao;
import com.codecool.petshelter.dao.PetDao;
import com.codecool.petshelter.model.Adoption;
import com.codecool.petshelter.model.Pet;
import com.codecool.petshelter.model.PetType;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("adoptions")
public class AdoptionEndpoint {

    private AdoptionDao dao;
    private PetDao petDao;

    @Inject
    public AdoptionEndpoint(AdoptionDao dao, PetDao petDao) {
        this.dao = dao;
        this.petDao = petDao;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAdoptions() {
        List<Adoption> adoptions = dao.getAllAdoptions();
        return Response.ok(adoptions).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addAdoption(Adoption adoption) {
        long id = dao.addAdoption(adoption);
        if (id < 0) return Response.status(400).entity("Adoption already exists").build();
        return Response.ok("Adoption added with id = " + id).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateAdoption(Adoption adoption) {
        if (dao.updateAdoption(adoption)) {
            return Response.ok("Adoption updated").build();
        }
        return Response.status(404).entity("Adoption you are trying to change dose not exist in database").build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAdoptionByID(@PathParam("id") long id) {
        Adoption adoption = dao.getAdoptionById(id);
        if(adoption == null) return Response.status(404).entity("There is no adoption in database with id = " + id).build();
        return Response.ok(adoption).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteAdoption(long id) {
        if(dao.removeAdoption(id)) {
            return Response.ok("Deleted successfully").build();
        }
        return Response.status(400).entity("Could not delete adoption").build();
    }

    @PUT
    @Path("{adoptionId}/{petType}/{petId}")
    public Response addPetToAdoption(@PathParam("adoptionId") long adoptionId, @PathParam("petType") String petType, @PathParam("petId") long petId) {
        Adoption adoption = dao.getAdoptionById(adoptionId);
        if (adoption == null) return Response.status(404).entity("There is no adoption in database with id = " + adoptionId).build();

        Pet pet = petDao.getPetById(petId, PetType.valueOf(petType.toUpperCase()));
        if (pet == null) return Response.status(404).entity("There is no " + petType + " in database with id = " + petId).build();

        adoption.setAdoptedPet(pet);
        dao.updateAdoption(adoption);
        return Response.ok("Pet added to adoption").build();
    }
}
