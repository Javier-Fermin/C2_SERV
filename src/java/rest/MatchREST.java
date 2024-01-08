/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.EJBMatchManager;
import ejb.MatchManagerLocal;
import entity.Match;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import java.util.List;
import java.util.logging.Level;
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
 * RESTful web service class exposing CRUD operations for {@link Match}
 * entities.
 *
 * @author imanol
 */
@Path("entities.match")
public class MatchREST {

    /**
     * Logger for class methods.
     */
    private static final Logger LOGGER
            = Logger.getLogger("javafxserverside");
    /**
     * EJB reference for business logic object.
     */
    @EJB
    private MatchManagerLocal ejb;

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createMatch(Match match) {
        try {
            LOGGER.log(Level.INFO, "MatchRESTful service: create {0}.", match);
            ejb.createMatch(match);
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE,
                    "MatchRESTful service: Exception creating match, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateMatch(Match match) {
        try {
            LOGGER.log(Level.INFO, "MatchRESTful service: update {0}.", match);
            ejb.updateMatch(match);
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE,
                    "MatchRESTful service: Exception updating match, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Match> findAllMatches() {
        List<Match> matches = null;
        try {
            LOGGER.log(Level.INFO, "MatchRESTful service: find all matches.");
            matches = ejb.findAllMatches();
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "MatchRESTful service: Exception reading all matches, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return matches;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Match findAMatch(@PathParam("id") Integer id) {
        Match match = null;
        try {
            LOGGER.log(Level.INFO, "MatchRESTful service: find Match by id={0}.", id);
            match = ejb.findAMatch(id);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "MatchRESTful service: Exception reading match by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return match;
    }

    @DELETE
    @Path("{id}")
    //@Consumes({"application/xml", "application/json"})
    public void delete(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "MatchRESTful service: delete Match by id={0}.", id);
            ejb.deleteMatch(ejb.findAMatch(id));
        } catch (ReadException | DeleteException ex) {
            LOGGER.log(Level.SEVERE,
                    "MatchRESTful service: Exception deleting match by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    @GET
    @Path("nickname/{nickname}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Match> findMatchesByUserNickname(@PathParam("nickname") String nickname) {
        List<Match> matches = null;
        try {
            LOGGER.log(Level.INFO, "MatchRESTful service: find all matches.");
            matches = ejb.findMatchesByUserNickname(nickname);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "MatchRESTful service: Exception reading all matches, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return matches;
    }

    @GET
    @Path("league/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Match> findMatchesByLeagueId(@PathParam("id") Integer id) {
        List<Match> matches = null;
        try {
            LOGGER.log(Level.INFO, "MatchRESTful service: find all league matches.");
            matches = ejb.findMatchesByLeagueId(id);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "MatchRESTful service: Exception reading all league matches, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return matches;
    }

    @GET
    @Path("tournament/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Match> findMatchesByTournamentId(@PathParam("id") Integer id) {
        List<Match> matches = null;
        try {
            LOGGER.log(Level.INFO, "MatchRESTful service: find all tournament matches.");
            matches = ejb.findMatchesByTournamentId(id);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "MatchRESTful service: Exception reading all tournament matches, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return matches;
    }
}
