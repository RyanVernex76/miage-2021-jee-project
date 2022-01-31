package fr.pantheonsorbonne.ufr27.miage.resource;

import fr.pantheonsorbonne.ufr27.miage.dao.PassengerDao;
import fr.pantheonsorbonne.ufr27.miage.dto.RemainingQuota;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/passenger")
public class PassengerResource {

    @Inject
    PassengerDao passengerDao;


    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Passenger getPassenger(@PathParam("id") int id) throws PassengerNotFoundException {
        return passengerDao.getPassenger(id);
    }

    @Path("/all")
    @GET
    public Passenger[] getPassengers(){
        return passengerDao.getPassengers();
    }

}
