package fr.pantheonsorbonne.ufr27.miage.resource;

import fr.pantheonsorbonne.ufr27.miage.dao.AutonomousCarDao;
import fr.pantheonsorbonne.ufr27.miage.exception.CarNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.AutonomousCar;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/car")
public class CarResource {

    @Inject
    AutonomousCarDao carDao;

    @Path("/recharge")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public AutonomousCar[] getCarsNeedRecharge() throws CarNotFoundException {
        return carDao.getNeedRechargeCars();
    }
}
