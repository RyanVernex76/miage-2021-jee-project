package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.PassengerService;
import fr.pantheonsorbonne.ufr27.miage.service.VenueService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("passenger")
    public class PassengerResource {

        @Inject
        PassengerService passengerService;

        @Path("")
        @POST
        public void addPassenger() {

        }

        @Path("")
        @GET
        public Response connectPassenger(@QueryParam("email") String email,
                                         @QueryParam("pwd") String pwd ) {
           try {
               return Response.status(200).entity(passengerService.connectPassenger(email,pwd)).build();
           } catch ( PassengerNotFoundException e){
               throw new WebApplicationException(404);
           }
        }


    }
