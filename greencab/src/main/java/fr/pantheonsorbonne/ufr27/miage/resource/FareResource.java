package fr.pantheonsorbonne.ufr27.miage.resource;

import fr.pantheonsorbonne.ufr27.miage.dao.FareDao;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Fare;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/fare")
public class FareResource {

    @Inject
    FareDao fareDao;


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

}
