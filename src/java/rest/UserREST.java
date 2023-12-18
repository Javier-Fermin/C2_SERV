/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.UserManagerLocal;
import entity.User;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import java.util.List;
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
@Path("entities.user")
public class UserREST {

    /**
     * Logger for class methods.
     */
    private static final Logger LOGGER
            = Logger.getLogger("javafxserverside");
    /**
     * EJB reference for business logic object.
     */
    @EJB
    private UserManagerLocal ejb;

    /**
     * RESTful POST method for creating {@link User} objects from XML
     * representation.
     *
     * @param user The object containing user data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createUser(User user) {
        try {
            LOGGER.log(Level.INFO, "UserRESTful service: create {0}.", user);
            ejb.createUser(user);
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception creating user, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * RESTful PUT method for updating {@link User} objects from XML
     * representation.
     *
     * @param user The object containing user data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateUser(User user) {
        try {
            LOGGER.log(Level.INFO, "UserRESTful service: update {0}.", user);
            ejb.updateUser(user);
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception updating user, {0}",
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
    public void deleteUser(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "UserRESTful service: delete User by id={0}.", id);
            ejb.deleteUser(ejb.findUserById(id));
        } catch (ReadException | DeleteException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception deleting user by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAllUsers() {
        List<User> users = null;
        try {
            LOGGER.log(Level.INFO, "UserRESTful service: find all Users.");
            users = ejb.findUsers();
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception reading all Users, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return users;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User findUserById(@PathParam("id") Integer id) {
        User user = null;
        try {
            LOGGER.log(Level.INFO, "UserRESTful service: find User by id={0}.", id);
            user = ejb.findUserById(id);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception reading user by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return user;

    }

    @GET
    @Path("email/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User findUserByEmail(@PathParam("email") String email) {
        User user = null;
        try {
            LOGGER.log(Level.INFO, "UserRESTful service: find User by email={0}.", email);
            user = ejb.findUserByMail(email);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception reading user by email, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return user;
    }
}
