package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.service.PassengerService;
import fr.pantheonsorbonne.ufr27.miage.service.VenueService;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


    @Path("passenger")
    public class PassengerResource {

        @Inject
        PassengerService passengerService;

        @Path("")
        @POST
        public void addPassenger() {

        }


    }
