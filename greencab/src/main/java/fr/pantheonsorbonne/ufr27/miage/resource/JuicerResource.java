package fr.pantheonsorbonne.ufr27.miage.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonParser;
import fr.pantheonsorbonne.ufr27.miage.dao.JuicerDao;
import fr.pantheonsorbonne.ufr27.miage.exception.JuicerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Juicer;
import org.apache.camel.util.json.JsonObject;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/juicer")
public class JuicerResource {

    @Inject
    JuicerDao juicerDao;


    @Path("/{id}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Juicer getJuicer(@PathParam("id") int id) throws JuicerNotFoundException {
        return juicerDao.getJuicer(id);
    }

    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    @GET
    public Juicer[] getJuicers(){
        return juicerDao.getJuicers();
    }

    @Path("/add")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void createJuicer(Juicer j) {
        this.juicerDao.insertNewJuicer(j);
    }
}
