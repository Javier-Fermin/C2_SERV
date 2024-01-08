package ejb;

import java.util.Set;

import entity.Player;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import java.util.List;
import javax.ejb.Local;
import javax.ejb.Stateless;

/**
 * EJB Local Interface for managing Player entity CRUD opreations
 * 
 * @author imanol
 */
@Local
public interface PlayerManagerLocal {
    /**
     * Finds a {@link Player} with the given email in the underlying application
     * storage.
     * 
     * @param email the email for Player to be found
     * @return the {@link Player} found
     * @throws ReadException
     */
    public Player findPlayerByMail(String email) throws ReadException;

    /**
     * Finds all {@link Player} in the underlying application storage.
     * 
     * @return a set of {@link Player} with all the players.
     * @throws ReadException If there is any Exception during processing.
     */
    public List<Player> findPlayers() throws ReadException;

    /**
     * Creates a {@link Player} in the underlying application storage.
     * 
     * @param player The new created player.
     * @throws CreateException If there is any Exception during processing.
     */
    public void createPlayer(Player player) throws CreateException;

    /**
     * Deletes a {@link Player} in the underlying application storage.
     * 
     * @param player The player to be found and deleted.
     * @throws DeleteException If there is any Exception during processing.
     */
    public void deletePlayer(Player player) throws DeleteException;

    /**
     * Updates a {@link Player} in the underlying application storage.
     * 
     * @param player The player with new changes.
     * @throws UpdateException If there is any Exception during processing.
     */
    public void updatePlayer(Player player) throws UpdateException;
    
    public Player findPlayerById(Integer id) throws ReadException;
}
