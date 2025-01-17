package fr.pantheonsorbonne.ufr27.miage.resource;

import fr.pantheonsorbonne.ufr27.miage.dao.PassengerDao;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;

import javax.inject.Inject;
import javax.ws.rs.*;
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
    @Produces({MediaType.APPLICATION_JSON})
    @GET
    public Passenger[] getPassengers(){
        return passengerDao.getPassengers();
    }

    @Path("/add")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void createPassenger(Passenger p){
        this.passengerDao.insertNewPassenger(p);
    }

    @Path("/{id}")
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    public void deletePassengerAccount(@PathParam("id") int id) {
        try {
            this.passengerDao.deletePassenger(id);
        }
        catch (PassengerNotFoundException e){
            throw new WebApplicationException("index invalide ", 404);
        }
    }

    @Path("/password/{id}")
    @PUT
    @Consumes({MediaType.TEXT_PLAIN})
    public void changePasseword(@PathParam("id") int id, String newPassword) {
        try {
            this.passengerDao.changePassengerPassword(id, newPassword);
        } catch (PassengerNotFoundException e){
            throw new WebApplicationException("Invalid id ", 404);
        }
    }


}
