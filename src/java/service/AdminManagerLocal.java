package java.service;

import java.util.Set;

import entity.Admin;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindException;
import exceptions.UpdateException;
import javax.ejb.Local;

/**
 * EJB Local Interface for managing Admin entity CRUD opreations
 * 
 * @author imanol
 */
@Local
public interface AdminManagerLocal {
    /**
     * Finds a {@link Admin} with the given email in the underlying application
     * storage.
     * 
     * @param email the email for Admin to be found
     * @return the {@link Admin} found
     * @throws FindException
     */
    public Admin findAdminByMail(String email) throws FindException;

    /**
     * Finds all {@link Admin} in the underlying application storage.
     * 
     * @return a set of {@link Admin} with all the admins.
     * @throws FindException If there is any Exception during processing.
     */
    public Set<Admin> findAdmins() throws FindException;

    /**
     * Creates a {@link Admin} in the underlying application storage.
     * 
     * @param admin The new created admin.
     * @throws CreateException If there is any Exception during processing.
     */
    public void createAdmin(Admin admin) throws CreateException;

    /**
     * Deletes a {@link Admin} in the underlying application storage.
     * 
     * @param admin The admin to be found and deleted.
     * @throws DeleteException If there is any Exception during processing.
     */
    public void deleteAdmin(Admin admin) throws DeleteException;

    /**
     * Updates a {@link Admin} in the underlying application storage.
     * 
     * @param admin The admin with new changes.
     * @throws UpdateException If there is any Exception during processing.
     * @throws DeleteException
     */
    public void updateAdmin(Admin admin) throws UpdateException, DeleteException;
}
