/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import java.util.logging.Logger;
import ejb.LeagueManageLocal;
import exception.*;
import entity.League;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
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
 * @author 2dam
 */
@Path("entities.League")
public class LeagueREST {

    private static final Logger LOGGER = Logger.getLogger("javafxserverside");

    /**
     * EJB for League entity
     */
    @EJB
    private LeagueManageLocal ejb;

    /**
     * REST method to create league, throws InternalServerErrorException if
     * error
     *
     * @param league to create the league
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(League league) {
        try {
            LOGGER.log(Level.INFO, "LeagueREST service: create.", league);
            ejb.createLeague(league);
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, "LeagueREST service: Exception creating league", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }

    }

    /**
     * REST method to update league by id, throws InternalServerErrorException
     * if error
     *
     * @param league to update the league
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void update(League league) {
        try {
            LOGGER.log(Level.INFO, "LeagueREST service: update.", league);
            ejb.updateLeague(league);
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "LeagueREST service: Exception updating league", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * REST method to delete league by id, throws InternalServerErrorException
     * if error
     *
     * @param id to find the league to remove
     */
    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "LeagueREST service: delete League.", id);
            ejb.deleteLeague(ejb.findOneLeague(id));
        } catch (ReadException | DeleteException ex) {
            LOGGER.log(Level.SEVERE, "LeagueREST service: Exception updating League", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * REST method to find league by id, throws InternalServerErrorException if
     * error
     *
     * @param id to find the league
     * @return league finded by id
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public League findPackById(@PathParam("id") Integer id) {
        League league = null;
        try {
            LOGGER.log(Level.INFO, "LeagueREST service: find league by ", id);
            league = ejb.findOneLeague(id);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "LeagueREST service: Exception reading league by id, {0}", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return league;
    }

    /**
     * REST method to find league by name, throws InternalServerErrorException
     * if error
     *
     * @param name to find the league
     * @return league finded by name
     */
    @GET
    @Path("league/{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<League> findLeagueByName(@PathParam("name") String name) {
        List<League> league = null;
        try {
            LOGGER.log(Level.INFO, "LeagueREST service: find league by ", name);
            league = ejb.findLeagueByName(name);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "LeagueREST service: Exception reading league by name, {0}", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return league;
    }

    /**
     * REST method to find all leagues, throws InternalServerErrorException if
     * error
     *
     * @return leagues finded
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<League> findAll() {
        List<League> leagues = null;
        try {
            LOGGER.log(Level.INFO, "LeagueREST service: find all leagues.");
            leagues = ejb.findAllLeagues();
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "LeagueREST service: Exception reading all leagues, {0}", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return leagues;
    }

    /**
     * REST method to find all unfinished leagues, throws
     * InternalServerErrorException if error
     *
     * @param today String to compare with the end date
     * @return leagues that are finished
     */
    @GET
    @Path("endDate/{endDate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<League> findAllFinishLeagues(@PathParam("endDate") String today) {
        List<League> leagues = null;
        try {
            OffsetDateTime odt = OffsetDateTime.parse(today);
            Instant instant = odt.toInstant(); // Instant is always in UTC.
            Date date = Date.from(instant);
            leagues=ejb.findAllFinishLeagues(date);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "LeagueREST service: Exception reading all leagues finished", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return leagues;
    }

    /**
     * REST method to find all unstarted leagues, throws
     * InternalServerErrorException if error
     *
     * @param today date to compare with the start date
     * @return leagues that are unstarted
     */
    @GET
    @Path("startDate/{startDate}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<League> findAllUnstartedLeagues(@PathParam("startDate") String today) {
        List<League> leagues = null;
        try {
            LOGGER.log(Level.INFO, "LeagueREST service: find leagues unstarted.");
            OffsetDateTime odt = OffsetDateTime.parse(today);
            Instant instant = odt.toInstant(); // Instant is always in UTC.
            Date date = Date.from(instant);
            leagues=ejb.findAllUnstartedLeagues(date);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "LeagueREST service: Exception reading all leagues unstarted", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return leagues;
    }

    /**
     * REST method to find league by match id, throws
     * InternalServerErrorException if error
     *
     * @param id from the match ti find the league
     * @return league finded by match
     */
    @GET
    @Path("match/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<League> findLeagueForMatch(@PathParam("id") Integer id) {
        List<League> league = null;
        try {
            LOGGER.log(Level.INFO, "LeagueREST service: find league by match ", id);
            league = ejb.findLeagueForMatch(id);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "LeagueREST service: Exception reading league by match id, {0}", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return league;
    }
}
