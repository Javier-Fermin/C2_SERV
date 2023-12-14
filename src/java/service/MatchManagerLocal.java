package java.service;

import exceptions.*;
import java.util.Set;

import entity.Match;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.UpdateException;
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
     * @throws FindException If there is any Exception during processing.
     */
    public Set<Match> findAllMatches() throws FindException;

    /**
     * Finds a set of {@link Match} played in a Tournament.
     * 
     * @return A set of {@link Match} that are played in Tournaments.
     * @throws FindException If there is any Exception during processing.
     */
    public Set<Match> findAllTournamentMatches() throws FindException;

    /**
     * Finds a set of {@link Match} played in a League.
     * 
     * @return A set of {@link Match} that are played in a League.
     * @throws FindException If there is any Exception during processing.
     */
    public Set<Match> findAllLeagueMatches() throws FindException;

    /**
     * Finds a {@link Match} by its id that is played in a Tournament.
     * 
     * @param id The id for the user to be found.
     * @return The {@link Match} object with all the information.
     * @throws FindException If there is any Exception during processing.
     */
    public Match findATournament(Integer id) throws FindException;

    /**
     * Finds a {@link Match} by its id that is played in a League.
     * 
     * @return The {@link Match} object with all the information.
     * @throws FindException If there is any Exception during processing.
     */
    public Match findALeague(Integer id) throws FindException;

    /**
     * Finds a {@link Match} by its id that is played in a specific Tournament.
     * 
     * @param id the id for the Tournament to be found.
     * @return A set of {@link Match} that are played in a specific Tournament.
     * @throws FindException If there is any Exception during processing.
     */
    public Set<Match> findMatchTournamentById(Integer id) throws FindException;

    /**
     * Finds a {@link Match} by its id that is played in a specific League.
     * 
     * @param id the id for the Tournament to be found.
     * @return A set of {@link Match} that are played in a specific League.
     * @throws FindException If there is any Exception during processing.
     */
    public Set<Match> findMatchLeagueById(Integer id) throws FindException;

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
     * @param match The match for the user to be found and deleted.
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
}
