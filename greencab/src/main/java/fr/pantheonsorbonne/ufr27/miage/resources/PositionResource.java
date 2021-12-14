package fr.pantheonsorbonne.ufr27.miage.resources;

import fr.pantheonsorbonne.ufr27.miage.dto.Position;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("position")
public class PositionResource {

    @PUT
    @Path("{positionableObjectId}")
    public void updatePosition(@PathParam("positionableObjectId") int positionableObjectId, Position position) {
        System.out.println(positionableObjectId + " " + position);


    }

}
