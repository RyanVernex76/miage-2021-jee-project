package fr.pantheonsorbonne.ufr27.miage.resource;

import fr.pantheonsorbonne.ufr27.miage.camel.gateways.RechargeGateway;
import fr.pantheonsorbonne.ufr27.miage.dao.JuicerDao;
import fr.pantheonsorbonne.ufr27.miage.dao.RechargeDao;
import fr.pantheonsorbonne.ufr27.miage.exception.JuicerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.exception.PassengerNotFoundException;
import fr.pantheonsorbonne.ufr27.miage.model.Juicer;
import fr.pantheonsorbonne.ufr27.miage.model.Recharge;
import fr.pantheonsorbonne.ufr27.miage.service.RechargeService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/juicer")
public class JuicerResource {

    @Inject
    JuicerDao juicerDao;

    @Inject
    RechargeDao rechargeDao;

    @Inject
    RechargeService rechargeService;

    @Inject
    RechargeGateway rechargeGateway;


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
        this.rechargeGateway.sendNewRecharge(r);
    }

    @Path("/{juicerId}/recharges")
    @GET
    @Consumes({MediaType.APPLICATION_JSON})
    public Recharge[] getJuicerRecharges(@PathParam("juicerId") int juicerId) throws JuicerNotFoundException {
        return this.rechargeDao.getJuicerRecharges(juicerId);
    }

    @Path("/{id}")
    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    public void deleteJuicerAccount(@PathParam("id") int id) {
        try {
            this.juicerDao.deleteJuicer(id);
        }
        catch (JuicerNotFoundException e){
            throw new WebApplicationException("index invalide ", 404);
        }
    }

    @Path("/password/{id}")
    @PUT
    @Consumes({MediaType.TEXT_PLAIN})
    public void changePasseword(@PathParam("id") int id, String newPassword) {
        try {
            this.juicerDao.changeJuicerPassword(id, newPassword);
        } catch (JuicerNotFoundException e){
            throw new WebApplicationException("Invalid id ", 404);
        }
    }
}
