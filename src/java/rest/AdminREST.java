/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.EJBAdminManager;
import entity.Admin;
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
@Path("entities.admin")
public class AdminREST {
    /**
     * Logger for class methods.
     */
    private static final Logger LOGGER
            = Logger.getLogger("javafxserverside");
    /**
     * EJB reference for business logic object.
     */
    @EJB
    private EJBAdminManager ejb;
    /**
     * RESTful POST method for creating {@link User} objects from XML
     * representation.
     *
     * @param admin The object containing user data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createAdmin(Admin admin) {
        try {
            LOGGER.log(Level.INFO,"AdminRESTful service: create {0}.",admin);
            ejb.createAdmin(admin);
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE, 
                    "AdminRESTful service: Exception creating admin, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    /**
     * RESTful PUT method for updating {@link User} objects from XML representation.
     * @param admin The object containing user data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateAdmin(Admin admin) {
        try {
            LOGGER.log(Level.INFO,"AdminRESTful service: update {0}.",admin);
            ejb.updateAdmin(admin);
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE,
                    "AdminRESTful service: Exception updating admin, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    /**
     * RESTful DELETE method for deleting {@link User} objects from id.
     * @param id The id for the object to be deleted.
     */
    @DELETE
    @Path("{id}")
    public void deleteAdmin(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO,"AdminRESTful service: delete Admin by id={0}.",id);
            ejb.deleteAdmin(ejb.findAdminById(id));
        } catch (ReadException | DeleteException ex) {
            LOGGER.log(Level.SEVERE,
                    "AdminRESTful service: Exception deleting admin by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        } 
    }
    
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Set<Admin> findAllAdmins() {
        Set<Admin> admins = null;
        try {
            LOGGER.log(Level.INFO, "AdminRESTful service: find all Admins.");
            admins = ejb.findAdmins();
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "AdminRESTful service: Exception reading all Admins, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return admins;
    }
    
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Admin findAdminById(@PathParam("id") Integer id) {
        Admin admin = null;
        try {
            LOGGER.log(Level.INFO, "AdminRESTful service: find Admin by id={0}.", id);
            admin = ejb.findAdminById(id);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "AdminRESTful service: Exception reading admin by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return admin;
    
    }
    
    @GET
    @Path("email/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Admin findPlayerByEmail(@PathParam("email") String email) {
        Admin admin = null;
        try {
            LOGGER.log(Level.INFO, "AdminRESTful service: find Admin by email={0}.", email);
            admin = ejb.findAdminByMail(email);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "AdminRESTful service: Exception reading admin by email, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return admin;
    
    }
}
