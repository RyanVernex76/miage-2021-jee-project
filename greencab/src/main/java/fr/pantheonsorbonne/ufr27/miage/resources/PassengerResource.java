package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.dto.ConnectionDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;

import fr.pantheonsorbonne.ufr27.miage.dto.PassengerDTO;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerAlreadyExistException;

import fr.pantheonsorbonne.ufr27.miage.service.PassengerService;


import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;


@Path("passenger")
    public class PassengerResource {

        @Inject
        PassengerService passengerService;

        @Path("register")
        @POST
        public void register(@QueryParam("passenger") PassengerDTO passengerDTO) {

            try{
                    this.passengerService.addNewPassenger(passengerDTO);
            } catch (PassengerAlreadyExistException e) {
                throw new WebApplicationException("An account with this email address already exists", 409);
            }

        }

        @Path("connection")
        @POST
        public Response connectPassenger(@QueryParam("connection") ConnectionDTO connectionDTO) {
           try {
               return Response.status(200).entity(passengerService.connectPassenger(connectionDTO)).build();
           } catch ( PassengerNotFoundException e){
               throw new WebApplicationException(404);
           }
        }


    }
