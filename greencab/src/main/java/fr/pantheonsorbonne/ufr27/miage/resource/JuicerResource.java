package fr.pantheonsorbonne.ufr27.miage.resource;

import fr.pantheonsorbonne.ufr27.miage.dao.JuicerDao;
import fr.pantheonsorbonne.ufr27.miage.dao.RechargeDao;
import fr.pantheonsorbonne.ufr27.miage.exception.JuicerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Juicer;
import fr.pantheonsorbonne.ufr27.miage.model.Recharge;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/juicer")
public class JuicerResource {

    @Inject
    JuicerDao juicerDao;

    @Inject
    RechargeDao rechargeDao;


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

    @Path("/recharge/add")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public void initRecharge(Recharge r) {
        this.rechargeDao.insertNewRecharge(r);
    }

    @Path("/{juicerId}/recharges")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Recharge[] getJuicerRecharges(@PathParam("juicerId") int juicerId) {
        return this.rechargeDao.getJuicerRecharges(juicerId);
    }
}
