package service;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Admin;

import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindException;
import exceptions.UpdateException;

/**
 * EJB class for managing Admin entity CRUD operations.
 * 
 * @author imanol
 */

public class EJBAdminManager implements AdminManagerLocal {
    /**
     * Logger for the class.
     */
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    /**
     * Entity manager object.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Finds a {@link Admin} with the given email in the underlying application
     * storage.
     * 
     * @param email the email for Admin to be found
     * @return the {@link Admin} found
     * @throws FindException
     */
    @Override
    public Admin findAdminByMail(String email) throws FindException {
        Admin admin = null;
        try {
            LOGGER.info("AdminManager: Finding the admin by email.");
            admin = em.find(Admin.class, email);
            if (admin != null) {
                LOGGER.log(Level.INFO, "AdminManager: Admin found {0}", admin.getEmail());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AdminManager: Exception Finding admin by email:", e.getMessage());
            throw new FindException(e.getMessage());
        }
        return admin;
    }

    /**
     * Finds all {@link Admin} in the underlying application storage.
     * 
     * @return a set of {@link Admin} with all the admins.
     * @throws FindException If there is any Exception during processing.
     */
    @Override
    public Set<Admin> findAdmins() throws FindException {
        Set<Admin> admins = null;
        try {
            LOGGER.info("AdminManager: Reading all admins.");
            admins = (Set<Admin>) em.createNamedQuery("findAdmins").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AdminManager: Exception reading all admins:",
                    e.getMessage());
            throw new FindException(e.getMessage());
        }
        return admins;
    }

    /**
     * Creates a {@link Admin} in the underlying application storage.
     * 
     * @param admin The new created admin.
     * @throws CreateException If there is any Exception during processing.
     */
    @Override
    public void createAdmin(Admin admin) throws CreateException {
        LOGGER.info("AdminManager: Creating admin.");
        try {
            em.persist(admin);
            LOGGER.info("AdminManager: Admin created.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AdminManager: Exception creating admin.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Deletes a {@link Admin} in the underlying application storage.
     * 
     * @param admin The admin to be found and deleted.
     * @throws DeleteException If there is any Exception during processing.
     */
    @Override
    public void deleteAdmin(Admin admin) throws DeleteException {
        LOGGER.info("AdminManager: Deleting admin.");
        try {
            admin = em.merge(admin);
            em.remove(admin);
            LOGGER.info("AdminManager: Admin deleted.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AdminManager: Exception deleting admin.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * Updates a {@link Admin} in the underlying application storage.
     * 
     * @param admin The admin with new changes.
     * @throws UpdateException If there is any Exception during processing.
     */
    @Override
    public void updateAdmin(Admin admin) throws UpdateException, DeleteException {
        LOGGER.info("AdminManager: Updating admin.");
        try {
            // if(!em.contains(admin))em.merge(admin);
            em.merge(admin);
            em.flush();
            LOGGER.info("AdminManager: Admin updated.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "AdminManager: Exception updating admin.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

}
