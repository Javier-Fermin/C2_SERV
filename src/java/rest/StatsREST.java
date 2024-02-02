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
import javax.ws.rs.core.MediaType;

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
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Stats stats) {
        try {
            LOGGER.info("StatsREST service: Creating stats");
            ejb.createStats(stats);
        } catch (CreateException ex) {
            LOGGER.severe("StatsREST service: Unexpected error occurred"+ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void update(Stats stats) {
        try {
            ejb.updateStats(stats);
        } catch (UpdateException ex) {
            LOGGER.severe("StatsREST service: Unexpected error occurred"+ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    @DELETE
    @Path("{matchId}/{playerId}")
    //@Consumes({"application/xml", "application/json"})
    public void delete(@PathParam("matchId") Integer matchId,@PathParam("playerId") Integer playerId) {
        try {
            LOGGER.info("StatsREST service: Deleting stats");
            ejb.deleteStats(ejb.findStatById(matchId, playerId));
        } catch (ReadException | DeleteException ex) {
            LOGGER.severe("StatsREST service: Unexpected error occurred"+ex.getMessage());
            throw new InternalServerErrorException(ex);
        } 
    }
    
    @GET
    @Path("player/{nickname}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Stats> findStatsByPlayerNickname(@PathParam("nickname") String nickname) {
        List<Stats> stats=null;
        try {
            stats=ejb.findStatsByPlayerNickname(nickname);
        } catch (ReadException ex) {
            LOGGER.severe("StatsREST service: Unexpected error occurred"+ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return stats;
    }
    
    @GET
    @Path("match/{description}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Stats> findStatsByMatchDescription(@PathParam("description") String description) {
        List<Stats> stats=null;
        try {
            stats=ejb.findStatsByMatchDescription(description);
        } catch (ReadException ex) {
            LOGGER.severe("StatsREST service: Unexpected error occurred"+ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return stats;
    }
    
    @GET
    @Path("league/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
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
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Stats> findStatsByTournamentName(@PathParam("name") String tournamentName) {
        List<Stats> stats=null;
        try {
            LOGGER.info("StatsREST service: Finding stats by tournament name.");
            stats=ejb.findStatsByTournamentName(tournamentName);
        } catch (ReadException ex) {
            LOGGER.severe("StatsREST service: Error while finding desired stats."+ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return stats;
    }
    
    @GET
    @Path("{matchId}/{playerId}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Stats find(@PathParam("matchId") Integer matchId,@PathParam("playerId") Integer playerId) {
        Stats stats=null;
        try {
            LOGGER.info("StatsREST service: Finding a stat by ID.");
            stats=ejb.findStatById(matchId, playerId);
        } catch (ReadException ex) {
            LOGGER.severe("StatsREST service: Error while finding desired stats."+ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return stats;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Stats> findAll() {
        List<Stats> users=null;
        try {
            LOGGER.info("StatsREST service: Finding all stats");
            users=ejb.findAllStats();
        } catch (ReadException ex) {
            LOGGER.severe("StatsREST service: Error while finding desired stats."+ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return users;
    }
}
