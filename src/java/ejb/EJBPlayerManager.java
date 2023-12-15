package ejb;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Player;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import javax.ejb.Stateless;

/**
 * EJB class for managing Player entity CRUD operations.
 * 
 * @author imanol
 */
@Stateless
public class EJBPlayerManager implements PlayerManagerLocal {
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
     * Finds a {@link Player} with the given email in the underlying application
     * storage.
     * 
     * @param email the email for Player to be found
     * @return the {@link Player} found
     * @throws ReadException
     */
    @Override
    public Player findPlayerByMail(String email) throws ReadException {
        Player player = null;
        try {
            LOGGER.info("PlayerManager: Finding the player by email.");
            player = em.find(Player.class, email);
            if (player != null) {
                LOGGER.log(Level.INFO, "PlayerManager: Player found {0}", player.getEmail());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PlayerManager: Exception Finding player by email:", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return player;
    }

    /**
     * Finds all {@link Player} in the underlying application storage.
     * 
     * @return a set of {@link Player} with all the players.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public Set<Player> findPlayers() throws ReadException {
        Set<Player> players = null;
        try {
            LOGGER.info("PlayerManager: Reading all players.");
            players = (Set<Player>) em.createNamedQuery("findPlayers").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PlayerManager: Exception reading all players:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return players;
    }

    /**
     * Creates a {@link Player} in the underlying application storage.
     * 
     * @param player The new created player.
     * @throws CreateException If there is any Exception during processing.
     */
    @Override
    public void createPlayer(Player player) throws CreateException {
        LOGGER.info("PlayerManager: Creating player.");
        try {
            em.persist(player);
            LOGGER.info("PlayerManager: Player created.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PlayerManager: Exception creating player.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Deletes a {@link Player} in the underlying application storage.
     * 
     * @param player The player to be found and deleted.
     * @throws DeleteException If there is any Exception during processing.
     */
    @Override
    public void deletePlayer(Player player) throws DeleteException {
        LOGGER.info("PlayerManager: Deleting player.");
        try {
            player = em.merge(player);
            em.remove(player);
            LOGGER.info("PlayerManager: Player deleted.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PlayerManager: Exception deleting player.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * Updates a {@link Player} in the underlying application storage.
     * 
     * @param player The player with new changes.
     * @throws UpdateException If there is any Exception during processing.
     */
    @Override
    public void updatePlayer(Player player) throws UpdateException {
        LOGGER.info("PlayerManager: Updating player.");
        try {
            // if(!em.contains(player))em.merge(player);
            em.merge(player);
            em.flush();
            LOGGER.info("PlayerManager: Player updated.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "PlayerManager: Exception updating player.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

}
