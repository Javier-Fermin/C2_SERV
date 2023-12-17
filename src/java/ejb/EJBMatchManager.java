package ejb;

import exception.DeleteException;
import exception.UpdateException;
import exception.CreateException;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entity.Match;
import exception.ReadException;
import javax.ejb.Stateless;
import javax.xml.registry.FindException;

/**
 * EJB class for managing Match entity CRUD operations.
 * 
 * @author imanol
 */
@Stateless
public class EJBMatchManager implements MatchManagerLocal {
    /**
     * Logger for the class
     */
    private static final Logger LOGGER = Logger.getLogger("javafxserverside");
    /**
     * Entity manager object
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Finds a set of {@link Match} objects.
     * 
     * @return A set of all Matches played .
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public Set<Match> findAllMatches() throws ReadException {
        Set<Match> matches = null;
        try {
            LOGGER.info("MatchManager: Reading all matchs.");
            matches = (Set<Match>) em.createNamedQuery("findAllMatches").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "MatchManager: Exception reading all matchs:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return matches;
    }

    /**
     * Finds a set of {@link Match} played in a Tournament.
     * 
     * @return A set of {@link Match} that are played in Tournaments.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public Set<Match> findAllTournamentMatches() throws ReadException {
        Set<Match> matches = null;
        try {
            LOGGER.info("MatchManager: Reading all matchs.");
            matches = (Set<Match>) em.createNamedQuery("findAllTournamentMatches").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "MatchManager: Exception reading all matchs:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return matches;
    }

    /**
     * Finds a set of {@link Match} played in a League.
     * 
     * @return A set of {@link Match} that are played in a League.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public Set<Match> findAllLeagueMatches() throws ReadException {
        Set<Match> matches = null;
        try {
            LOGGER.info("MatchManager: Reading all matchs.");
            matches = (Set<Match>) em.createNamedQuery("findAllLeagueMatches").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "MatchManager: Exception reading all matchs:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return matches;
    }

    /**
     * Finds a {@link Match} by its id that is played in a Tournament.
     * 
     * @param id The id for the match to be found.
     * @return The {@link Match} object with all the information.
     * @throws ReadException If there is any Exception during processing.
     */

    /**
     * Finds a {@link Match} by its id that is played in a League.
     * 
     * @return The {@link Match} object with all the information.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public Match findAMatch(Integer id) throws ReadException {
       Match match = null;
        try {
            LOGGER.info("MatchManager: Finding the match in a league by id.");
            match = em.find(Match.class, id);
            if (match != null) {
                LOGGER.log(Level.INFO, "MatcManager: Match found {0}", match.getId());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "MatcManager: Exception Finding a match by id:", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return match;
    }

    /**
     * Finds a {@link Match} by its id that is played in a specific Tournament.
     * 
     * @param id the id for the Tournament to be found.
     * @return A set of {@link Match} that are played in a specific Tournament.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public Set<Match> findMatchesByTournamentId(Integer id) throws ReadException {
        Set<Match> match = null;
        try {
            LOGGER.info("MatchManager: Finding the matches played in tournaments by id.");
            match = (Set<Match>) em.createNamedQuery("findMatchTournamentById").setParameter("id", id).getResultList();
            if (match != null) {
                LOGGER.log(Level.INFO, "MatchManager: Matches found: {0}", match.size());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "MatchManager: Exception Finding match by id:", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return match;
    }

    /**
     * Finds a {@link Match} by its id that is played in a specific League.
     * 
     * @param id the id for the Tournament to be found.
     * @return A set of {@link Match} that are played in a specific League.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public Set<Match> findMatchesByLeagueId(Integer id) throws ReadException {
        Set<Match> match = null;
        try {
            LOGGER.info("MatchManager: Finding the matches played in leagues by id.");
            match = (Set<Match>) em.createNamedQuery("findMatchLeagueById").setParameter("id", id).getResultList();
            if (match != null) {
                LOGGER.log(Level.INFO, "MatchManager: Matches found: {0}", match.size());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "MatchManager: Exception Finding match by id:", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return match;
    }

    /**
     * Creates a {@link Match} in the underlying application storage.
     * 
     * @param match The new created match.
     * @throws CreateException If there is any Exception during processing.
     */
    @Override
    public void createMatch(Match match) throws CreateException {
        LOGGER.info("MatchManager: Creating match.");
        try {
            em.persist(match);
            LOGGER.info("MatchManager: Match created.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "MatchManager: Exception creating match.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    @Override
    public void deleteMatch(Match match) throws DeleteException {
        LOGGER.info("MatchManager: Deleting match.");
        try {
            match = em.merge(match);
            em.remove(match);
            LOGGER.info("MatchManager: Match deleted.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "MatchManager: Exception deleting match.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * Deletes a {@link Match} in the underlying application storage.
     * 
     * @param match The match for the match to be found and deleted.
     * @throws DeleteException If there is any Exception during processing.
     */
    @Override
    public void updateMatch(Match match) throws UpdateException {
        LOGGER.info("MatchManager: Updating match.");
        try {
            // if(!em.contains(match))em.merge(match);
            em.merge(match);
            em.flush();
            LOGGER.info("MatchManager: Match updated.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "MatchManager: Exception updating match.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    @Override
    public Set<Match> findMatchesByUserNickname(String nickname) throws ReadException {
        Set<Match> match = null;
        try {
            LOGGER.info("MatchManager: Finding the matches played by the introduced nicknaem");
            match = (Set<Match>) em.createNamedQuery("findMatchesByUserNickname").setParameter("nickname", nickname).getResultList();
            if (match != null) {
                LOGGER.log(Level.INFO, "MatchManager: Matches found: {0}", match.size());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "MatchManager: Exception Finding match by nickname:", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return match;
    }

}
