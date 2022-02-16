package fr.pantheonsorbonne.ufr27.miage.resource;

import fr.pantheonsorbonne.ufr27.miage.dao.PositionDao;
import fr.pantheonsorbonne.ufr27.miage.dto.Position;
import fr.pantheonsorbonne.ufr27.miage.exception.ElementNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.service.GoogleMapService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/position")
public class PositionResource {

    @Inject
    PositionDao posDao;

    @Inject
    GoogleMapService maps;

    @Path("/{id}")
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String getPosition(@PathParam("id") int id) throws ElementNotFoundException {
        Position p = posDao.getPosition(id);
        return maps.getAddress(p);
    }
}
