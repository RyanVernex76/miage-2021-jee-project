package fr.pantheonsorbonne.ufr27.miage.resource;

import fr.pantheonsorbonne.ufr27.miage.camel.gateways.FareGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.FareDao;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Fare;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/fare")
public class FareResource {

    @Inject
    FareDao fareDao;

    @Inject
    FareGateway fareGateway;


    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Fare getFare(@PathParam("id") int id) {
        return fareDao.getFare(id);
    }

    @Path("/passenger/{idPassenger}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Fare[] getFares(@PathParam("idPassenger") int passengerId) throws PassengerNotFoundException {
        return fareDao.getPassengerFares(passengerId);
    }

    @Path("/passenger/{idPassenger}/newFare")
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public fr.pantheonsorbonne.ufr27.miage.dto.Fare bookFare(@PathParam("idPassenger") int passengerId, @QueryParam("location") String from) throws PassengerNotFoundException {
        fr.pantheonsorbonne.ufr27.miage.dto.Fare f = new fr.pantheonsorbonne.ufr27.miage.dto.Fare(from, passengerId);
        return this.fareGateway.sendFareToAvailableCar(f);
    }
}
