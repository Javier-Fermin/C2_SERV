package ejb;

import java.util.Set;

import entity.Match;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import javax.ejb.Local;

//import javax.ejb.Local;
/**
 * EJB Local Interface for managing Match entity CRUD opreations
 * 
 * @author imanol
 */
@Local
public interface MatchManagerLocal {
    /**
     * Finds a set of {@link Match} objects.
     * 
     * @return A set of all Matches played .
     * @throws ReadException If there is any Exception during processing.
     */
    public Set<Match> findAllMatches() throws ReadException;

    /**
     * Finds a set of all {@link Match} .
     * 
     * @return A set of {@link Match} that are played in Tournaments.
     * @throws ReadException If there is any Exception during processing.
     */
    public Set<Match> findAllTournamentMatches() throws ReadException;

    /**
     * Finds a set of {@link Match} played in a League.
     * 
     * @return A set of {@link Match} that are played in a League.
     * @throws ReadException If there is any Exception during processing.
     */
    public Set<Match> findAllLeagueMatches() throws ReadException;

    /**
     * Finds a {@link Match} by its id.
     * 
     * @param id The id for the match to be found.
     * @return The {@link Match} object with all the information.
     * @throws ReadException If there is any Exception during processing.
     */
    public Match findAMatch(Integer id) throws ReadException;

    /**
     * Finds a {@link Match} by its id that is played in a specific Tournament.
     * 
     * @param id the id for the Tournament to be found.
     * @return A set of {@link Match} that are played in a specific Tournament.
     * @throws ReadException If there is any Exception during processing.
     */
    public Set<Match> findMatchesByTournamentId(Integer id) throws ReadException;

    /**
     * Finds a {@link Match} by its id that is played in a specific League.
     * 
     * @param id the id for the Tournament to be found.
     * @return A set of {@link Match} that are played in a specific League.
     * @throws ReadException If there is any Exception during processing.
     */
    public Set<Match> findMatchesByLeagueId(Integer id) throws ReadException;

    /**
     * Creates a {@link Match} in the underlying application storage.
     * 
     * @param match The new created match.
     * @throws CreateException If there is any Exception during processing.
     */
    public void createMatch(Match match) throws CreateException;

    /**
     * Deletes a {@link Match} in the underlying application storage.
     * 
     * @param match The match to be found and deleted.
     * @throws DeleteException If there is any Exception during processing.
     */
    public void deleteMatch(Match match) throws DeleteException;

    /**
     * Updated a {@link Match} in the underlying application storage.
     * 
     * @param match The match updated with new changes.
     * @throws UpdateException If there is any Exception during processing.
     */
    public void updateMatch(Match match) throws UpdateException;
    
    /**
     *
     * @param nickname
     * @return
     * @throws FindException
     */
    public Set<Match> findMatchesByUserNickname(String nickname) throws ReadException;
}
