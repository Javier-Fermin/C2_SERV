/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.EJBPlayerManager;
import entity.Player;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import java.util.Set;
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
 *
 * @author imanol
 */
@Path("entities.player")
public class PlayerREST {

    /**
     * Logger for class methods.
     */
    private static final Logger LOGGER
            = Logger.getLogger("javafxserverside");
    /**
     * EJB reference for business logic object.
     */
    @EJB
    private EJBPlayerManager ejb;

    /**
     * RESTful POST method for creating {@link User} objects from XML
     * representation.
     *
     * @param player The object containing user data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createPlayer(Player player) {
        try {
            LOGGER.log(Level.INFO, "PlayerRESTful service: create {0}.", player);
            ejb.createPlayer(player);
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE,
                    "PlayerRESTful service: Exception creating player, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * RESTful PUT method for updating {@link User} objects from XML
     * representation.
     *
     * @param player The object containing user data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updatePlayer(Player player) {
        try {
            LOGGER.log(Level.INFO, "PlayerRESTful service: update {0}.", player);
            ejb.updatePlayer(player);
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE,
                    "PlayerRESTful service: Exception updating player, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * RESTful DELETE method for deleting {@link User} objects from id.
     *
     * @param id The id for the object to be deleted.
     */
    @DELETE
    @Path("{id}")
    //@Consumes({"application/xml", "application/json"})
    public void deletePlayer(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "PlayerRESTful service: delete Player by id={0}.", id);
            ejb.deletePlayer(ejb.findPlayerById(id));
        } catch (ReadException | DeleteException ex) {
            LOGGER.log(Level.SEVERE,
                    "PlayerRESTful service: Exception deleting player by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Set<Player> findAllPlayers() {
        Set<Player> players = null;
        try {
            LOGGER.log(Level.INFO, "PlayerRESTful service: find all Players.");
            players = ejb.findPlayers();
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "PlayerRESTful service: Exception reading all Players, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return players;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Player findPlayerById(@PathParam("id") Integer id) {
        Player player = null;
        try {
            LOGGER.log(Level.INFO, "PlayerRESTful service: find Player by id={0}.", id);
            player = ejb.findPlayerById(id);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "PlayerRESTful service: Exception reading player by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return player;

    }

    @GET
    @Path("email/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Player findPlayerByEmail(@PathParam("email") String email) {
        Player player = null;
        try {
            LOGGER.log(Level.INFO, "PlayerRESTful service: find Player by email={0}.", email);
            player = ejb.findPlayerByMail(email);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "PlayerRESTful service: Exception reading player by email, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return player;

    }
}
