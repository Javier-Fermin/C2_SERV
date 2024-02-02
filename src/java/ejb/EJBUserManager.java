package ejb;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.User;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import java.util.List;
import javax.ejb.Stateless;

/**
 * EJB class for managing User entity CRUD operations.
 * 
 * @author imanol
 */
@Stateless
public class EJBUserManager implements UserManagerLocal {
    /**
     * Logger for the class.
     */
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    /**
     * Entity manager object.
     */
    @PersistenceContext(unitName = "C2PU")
    private EntityManager em;

    /**
     * Finds a {@link User} with the given email in the underlying application
     * storage.
     * 
     * @param email the email for User to be found
     * @return the {@link User} found
     * @throws ReadException
     */
    @Override
    public List<User> findUserByMail(String email) throws ReadException {
        List<User> users = null;
        try {
            LOGGER.info("UserManager: Finding the user by email.");
            users = em.createNamedQuery("findUserByMail").setParameter("email", email).getResultList();
            if (users != null) {
                LOGGER.log(Level.INFO, "UserManager: User found {0}", users.get(0).getEmail());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception Finding user by email:", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return users;
    }

    /**
     * Finds all {@link User} in the underlying application storage.
     * 
     * @return a set of {@link User} with all the users.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public List<User> findUsers() throws ReadException {
        List<User> users = null;
        try {
            LOGGER.info("UserManager: Reading all users.");
            users = em.createNamedQuery("findUsers").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception reading all users:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return users;
    }

    /**
     * Creates a {@link User} in the underlying application storage.
     * 
     * @param user The new created user.
     * @throws CreateException If there is any Exception during processing.
     */
    @Override
    public void createUser(User user) throws CreateException {
        LOGGER.info("UserManager: Creating user.");
        try {
            em.persist(user);
            LOGGER.info("UserManager: User created.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception creating user.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Deletes a {@link User} in the underlying application storage.
     * 
     * @param user The user to be found and deleted.
     * @throws DeleteException If there is any Exception during processing.
     */
    @Override
    public void deleteUser(User user) throws DeleteException {
        LOGGER.info("UserManager: Deleting user.");
        try {
            user = em.merge(user);
            em.remove(user);
            LOGGER.info("UserManager: User deleted.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception deleting user.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * Updates a {@link User} in the underlying application storage.
     * 
     * @param user The user with new changes.
     * @throws UpdateException If there is any Exception during processing.
     */
    @Override
    public void updateUser(User user) throws UpdateException {
        LOGGER.info("UserManager: Updating user.");
        try {
            // if(!em.contains(user))em.merge(user);
            em.merge(user);
            em.flush();
            LOGGER.info("UserManager: User updated.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception updating user.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public User findUserById(Integer id) throws ReadException {
         User user = null;
        try {
            LOGGER.info("UserManager: Finding the user by email.");
            user = em.find(User.class, id);
            if (user != null) {
                LOGGER.log(Level.INFO, "UserManager: User found {0}", user.getId());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "UserManager: Exception Finding user by email:", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return user;
    }

    @Override
    public User recoverPassword(String passwd, String email) throws ReadException {
        try {    
            List<User> users = null;
            users = findUserByMail(email);
            User user = null;
            if(!users.isEmpty()){
                user = users.get(0);
                user.setPasswd(passwd);
                updateUser(user);
            }
            //user.setPasswd(null);
            return user;
        } catch (UpdateException ex) {
            Logger.getLogger(EJBUserManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
