/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Stats;
import entity.StatsId;
import exception.*;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This class is the EJB for managing Stats CRUD operations
 * 
 * @author Javier
 */
@Stateless
public class StatsManagerEJB implements StatsManagerEJBLocal{
    /**
     * Logger for the class
     */
    private static final Logger LOGGER = Logger.getLogger(StatsManagerEJB.class.getName());
    
    /**
     * Entity manager
     */
    @PersistenceContext(unitName = "C2PU")
    private EntityManager em;
    
    @Override
    public Stats findStatById(Integer matchId, Integer playerId) throws ReadException {
        Stats stats = null;
        try{
            StatsId id = new StatsId(matchId, playerId);
            LOGGER.info("Finding stats by id.");
            stats = em.find(Stats.class, id);
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during finding stats by idx: "+e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return stats;
    }

    /**
     * Finds a list of {@link Stats} with all the possible Stats
     * 
     * @return the list of {@link Stats} with all the data found 
     * @throws ReadException if there is any exception during the method
     */
    @Override
    public List<Stats> findAllStats() throws ReadException {
        List<Stats> stats = null;
        try{
            LOGGER.info("Finding all stats.");
            stats = em.createNamedQuery("findAllStats")
                    .getResultList();
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during finding all stats: "+e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return stats;
    }

    /**
     * Finds a list of {@link Stats} by a player nickname
     * 
     * @param nickname the player nickname to be searched
     * @return the list of {@link Stats} that fullfilled the requirements
     * @throws ReadException if there is any exception during the execution
     */
    @Override
    public List<Stats> findStatsByPlayerNickname(String nickname) throws ReadException {
        List<Stats> stats = null;
        try{
            LOGGER.info("Finding stats by player nickname.");
            stats = em.createNamedQuery("findStatsByPlayerNickname")
                    .setParameter("nickname", nickname)
                    .getResultList();
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during finding stats by player nickname: "+e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return stats;
    }

    /**
     * Finds a list of {@link Stats} by a match id
     * 
     * @param matchId the match id to be searched
     * @return the list of {@link Stats} that fullfilled the requirements
     * @throws ReadException if there is any exception during the method
     */
    @Override
    public List<Stats> findStatsByMatchDescription(String matchDescription) throws ReadException {
        List<Stats> stats = null;
        try{
            LOGGER.info("Finding stats by match id.");
            stats = em.createNamedQuery("findStatsByMatchDescription")
                    .setParameter("matchDescription", matchDescription)
                    .getResultList();
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during finding stats by match id: "+e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return stats;
    }

    /**
     * Finds a list of {@link Stats} by the league name
     * 
     * @param leagueName the league name to be searched
     * @return the list of {@link Stats} that fullfilled the requirements
     * @throws ReadException if there is any exception during the execution
     */
    @Override
    public List<Stats> findStatsByLeagueName(String leagueName) throws ReadException {
        List<Stats> stats = null;
        try{
            LOGGER.info("Finding stats by league name.");
            stats = em.createNamedQuery("findStatsByLeagueName")
                    .setParameter("leagueName", leagueName)
                    .getResultList();
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during finding stats by league name: "+e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return stats;
    }

    /**
     * Finds a list of {@link Stats} by the tournament name
     * 
     * @param tournamentName the tournament name to be searched
     * @return the list of {@link Stats} that fullfilled the requirements
     * @throws ReadException if there is any exception during the execution
     */
    @Override
    public List<Stats> findStatsByTournamentName(String tournamentName) throws ReadException {
        List<Stats> stats = null;
        try{
            LOGGER.info("Finding stats by tournament name.");
            stats = em.createNamedQuery("findStatsByTournamentName")
                    .setParameter("tournamentName", tournamentName)
                    .getResultList();
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during finding stats by tournament name: "+e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return stats;
    }

    /**
     * Creates a Stats in the database
     * 
     * @param stats the Stats to be created
     * @throws CreateException  if there is any exception during the method
     */
    @Override
    public void createStats(Stats stats) throws CreateException {
        try{
            LOGGER.info("Creating Stats.");
            em.persist(stats);
            LOGGER.info("Stats created.");
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during creation: "+e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Updates a Stats data in the database
     * 
     * @param stats the Stats to be updated with the data changes
     * @throws UpdateException if there is any exception during the method
     */
    @Override
    public void updateStats(Stats stats) throws UpdateException {
        try{
            LOGGER.info("Updating Stats.");
            em.merge(stats);
            em.flush();
            LOGGER.info("Stats updated.");
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during update: "+e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * Deletes a Stats from the database
     * 
     * @param stats the desired Stats to be deleted
     * @throws DeleteException if there is any exception during the execution
     */
    @Override
    public void deleteStats(Stats stats) throws DeleteException {
        try{
            LOGGER.info("Deleting Stats.");
            stats = em.merge(stats);
            em.remove(stats);
            LOGGER.info("Stats deleted.");
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during delete: "+e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }
}
