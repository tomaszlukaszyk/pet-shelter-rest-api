package com.codecool.petshelter.endpoints;

import com.codecool.petshelter.dao.CaretakerDao;
import com.codecool.petshelter.model.Caretaker;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/caretaker")
public class CaretakerEndpoint {

    private CaretakerDao dao;

    @Inject
    public CaretakerEndpoint(CaretakerDao dao) {
        this.dao = dao;
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
}
