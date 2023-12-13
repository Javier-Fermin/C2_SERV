/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Admin;
import entity.Stats;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author javie
 */
public class AdminManagerEJB implements AdminManagerEJBLocal{
    /**
     * Logger for the class
     */
    private static final Logger LOGGER = Logger.getLogger(StatsManagerEJB.class.getName());
    
    /**
     * Entity manager
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Finds a {@link Admin} by its id
     * 
     * @param id the id of the desired admin
     * @return the {@link Admin} with the given id
     * @throws ReadException if there is an exception during the method
     */
    @Override
    public Admin findAdminById(Integer id) throws ReadException {
        Admin admin = null;
        try{
            LOGGER.info("Finding amin by id.");
            admin = em.find(Admin.class, id);
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during finding admin by idx: "+e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return admin;
    }

    /**
     * Finds a set of {@link Stats} with all the possible Stats
     * 
     * @return the set of {@link Stats} with all the data found 
     * @throws ReadException if there is any exception during the method
     */
    @Override
    public Set<Admin> findAllAdmins() throws ReadException {
        Set<Admin> admins = null;
        try{
            LOGGER.info("Finding all admins.");
            admins = new LinkedHashSet<Admin>(
                    em.createNamedQuery("findAllAdmins")
                    .getResultList()
            );
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during finding all admins: "+e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return admins;
    }

    @Override
    public void createAdmin(Admin admin) throws CreateException {
        try{
            LOGGER.info("Creating admin.");
            em.persist(admin);
            LOGGER.info("Admin created.");
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during creation: "+e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void updateAdmin(Admin admin) throws UpdateException {
        try{
            LOGGER.info("Updating admin.");
            em.merge(admin);
            em.flush();
            LOGGER.info("Admin updated.");
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during update: "+e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public void deleteAdmin(Admin admin) throws DeleteException {
        try{
            LOGGER.info("Deleting admin.");
            admin = em.merge(admin);
            em.remove(admin);
            LOGGER.info("Admin deleted.");
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during admin delete: "+e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }
}
