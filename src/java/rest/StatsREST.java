/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.StatsManagerEJBLocal;
import entity.Stats;
import exception.*;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author javie
 */
@Path("stats")
public class StatsREST {
    
    private static final Logger LOGGER = Logger.getLogger(StatsREST.class.getName());
    
    @EJB
    private StatsManagerEJBLocal ejb;
    
    @POST
    @Consumes({"application/xml"})
    public void create(Stats stats) {
        try {
            LOGGER.info("Creating stats");
            ejb.createStats(stats);
        } catch (CreateException ex) {
            LOGGER.severe("Unexpected error occurred"+ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    @PUT
    @Consumes({"application/xml"})
    public void update(Stats stats) {
        try {
            ejb.updateStats(stats);
        } catch (UpdateException ex) {
            throw new InternalServerErrorException(ex);
        }
    }
    
    @DELETE
    @Path("{id}")
    //@Consumes({"application/xml", "application/json"})
    public void delete(@PathParam("id") Integer id) {
        try {
            ejb.deleteStats(ejb.findStatById(id));
        } catch (ReadException | DeleteException ex) {
            throw new InternalServerErrorException(ex);
        } 
    }
    
    @GET
    @Path("player/{nickname}")
    @Produces({"application/xml"})
    public List<Stats> findStatsByPlayerNickname(@PathParam("nickname") String nickname) {
        List<Stats> stats=null;
        try {
            stats=ejb.findStatsByPlayerNickname(nickname);
        } catch (ReadException ex) {
            throw new InternalServerErrorException(ex);
        }
        return stats;
    }
    
    @GET
    @Path("match/{id}")
    @Produces({"application/xml"})
    public List<Stats> findStatsByMatchId(@PathParam("id") Integer id) {
        List<Stats> stats=null;
        try {
            stats=ejb.findStatsByMatchId(id);
        } catch (ReadException ex) {
            throw new InternalServerErrorException(ex);
        }
        return stats;
    }
    
    @GET
    @Path("league/{name}")
    @Produces({"application/xml"})
    public List<Stats> findStatsByLeagueName(@PathParam("name") String leagueName) {
        List<Stats> stats=null;
        try {
            stats=ejb.findStatsByLeagueName(leagueName);
        } catch (ReadException ex) {
            throw new InternalServerErrorException(ex);
        }
        return stats;
    }
    
    @GET
    @Path("tournament/{name}")
    @Produces({"application/xml"})
    public List<Stats> findStatsByTournamentName(@PathParam("name") String tournamentName) {
        List<Stats> stats=null;
        try {
            stats=ejb.findStatsByTournamentName(tournamentName);
        } catch (ReadException ex) {
            throw new InternalServerErrorException(ex);
        }
        return stats;
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/xml"})
    public Stats find(@PathParam("id") Integer id) {
        Stats stats=null;
        try {
            stats=ejb.findStatById(id);
        } catch (ReadException ex) {
            throw new InternalServerErrorException(ex);
        }
        return stats;
    }
    
    @GET
    @Produces({"application/xml"})
    public List<Stats> findAll() {
        List<Stats> users=null;
        try {
            users=ejb.findAllStats();
        } catch (ReadException ex) {
            throw new InternalServerErrorException(ex);
        }
        return users;
    }
}
