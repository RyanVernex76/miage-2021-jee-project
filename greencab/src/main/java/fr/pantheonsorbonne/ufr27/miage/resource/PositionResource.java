package fr.pantheonsorbonne.ufr27.miage.resource;

import com.google.maps.errors.ApiException;
import fr.pantheonsorbonne.ufr27.miage.dao.PositionDao;
import fr.pantheonsorbonne.ufr27.miage.dto.Position;
import fr.pantheonsorbonne.ufr27.miage.exception.ElementNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Passenger;
import fr.pantheonsorbonne.ufr27.miage.service.GoogleMapService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

@Path("/position")
public class PositionResource {

    @Inject
    PositionDao posDao;

    @Inject
    GoogleMapService maps;

    @Path("/{id}")
    @GET
    @Produces({MediaType.TEXT_PLAIN})
    public String getPosition(@PathParam("id") int id) throws IOException, InterruptedException, ApiException, ElementNotFoundException {
        Position p = posDao.getPosition(id);
        return maps.getAddress(p);
    }
}
