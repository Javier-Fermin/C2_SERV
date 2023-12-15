package ejb;

import java.util.Set;

import entity.User;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
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
     * @throws ReadException
     */
    public User findUserByMail(String email) throws ReadException;

    /**
     * Finds all {@link User} in the underlying application storage.
     * 
     * @return a set of {@link User} with all the users.
     * @throws ReadException If there is any Exception during processing.
     */
    public Set<User> findUsers() throws ReadException;

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
    /**
     * 
     * @param id
     * @return
     * @throws exception.ReadException
     */
    public User findUserById(Integer id) throws ReadException;

}
