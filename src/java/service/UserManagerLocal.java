package java.service;

import java.util.Set;

import entity.User;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.FindException;
import exceptions.UpdateException;
import javax.ejb.Local;

/**
 * EJB Local Interface for managing User entity CRUD opreations
 * 
 * @author imanol
 */
@Local
public interface UserManagerLocal {
    /**
     * Finds a {@link User} with the given email in the underlying application
     * storage.
     * 
     * @param email the email for User to be found
     * @return the {@link User} found
     * @throws FindException
     */
    public User findUserByMail(String email) throws FindException;

    /**
     * Finds all {@link User} in the underlying application storage.
     * 
     * @return a set of {@link User} with all the users.
     * @throws FindException If there is any Exception during processing.
     */
    public Set<User> findUsers() throws FindException;

    /**
     * Creates a {@link User} in the underlying application storage.
     * 
     * @param user The new created user.
     * @throws CreateException If there is any Exception during processing.
     */
    public void createUser(User user) throws CreateException;

    /**
     * Deletes a {@link User} in the underlying application storage.
     * 
     * @param user The user to be found and deleted.
     * @throws DeleteException If there is any Exception during processing.
     */
    public void deleteUser(User user) throws DeleteException;

    /**
     * Updates a {@link User} in the underlying application storage.
     * 
     * @param user The user with new changes.
     * @throws UpdateException If there is any Exception during processing.
     */
    public void updateUser(User user) throws UpdateException;

}
