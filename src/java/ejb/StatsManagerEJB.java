/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Stats;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.LinkedHashSet;
import java.util.Set;
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
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Finds a {@link Stats} by its id
     * 
     * @param id the id of the desired stats
     * @return the {@link Stats} with the given id
     * @throws ReadException if there is an exception during the method
     */
    @Override
    public Stats findStatById(Integer id) throws ReadException {
        Stats stats = null;
        try{
            LOGGER.info("Finding stats by id.");
            stats = em.find(Stats.class, id);
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during finding stats by idx: "+e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return stats;
    }

    /**
     * Finds a set of {@link Stats} with all the possible Stats
     * 
     * @return the set of {@link Stats} with all the data found 
     * @throws ReadException if there is any exception during the method
     */
    @Override
    public Set<Stats> findAllStats() throws ReadException {
        Set<Stats> stats = null;
        try{
            LOGGER.info("Finding all stats.");
            stats = new LinkedHashSet<Stats>(
                    em.createNamedQuery("findAllStats")
                    .getResultList()
            );
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during finding all stats: "+e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return stats;
    }

    /**
     * 
     * 
     * @param nickname
     * @return
     * @throws ReadException 
     */
    @Override
    public Set<Stats> findStatsByPlayerNickname(String nickname) throws ReadException {
        Set<Stats> stats = null;
        try{
            LOGGER.info("Finding stats by player nickname.");
            stats = new LinkedHashSet<Stats>(
                    em.createNamedQuery("findStatsByLeagueName")
                    .setParameter("nickname", nickname)
                    .getResultList()
            );
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during finding stats by player nickname: "+e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return stats;
    }

    /**
     * 
     * @param matchId
     * @return
     * @throws ReadException 
     */
    @Override
    public Set<Stats> findStatsByMatchId(Integer matchId) throws ReadException {
        Set<Stats> stats = null;
        try{
            LOGGER.info("Finding stats by match id.");
            stats = new LinkedHashSet<Stats>(
                    em.createNamedQuery("findStatsByMatchId")
                    .setParameter("matchId", matchId)
                    .getResultList()
            );
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during finding stats by match id: "+e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return stats;
    }

    /**
     * 
     * @param leagueName
     * @return
     * @throws ReadException 
     */
    @Override
    public Set<Stats> findStatsByLeagueName(String leagueName) throws ReadException {
        Set<Stats> stats = null;
        try{
            LOGGER.info("Finding stats by league name.");
            stats = new LinkedHashSet<Stats>(
                    em.createNamedQuery("findStatsByLeagueName")
                    .setParameter("leagueName", leagueName)
                    .getResultList()
            );
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during finding stats by league name: "+e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return stats;
    }

    /**
     * 
     * @param tournamentName
     * @return
     * @throws ReadException 
     */
    @Override
    public Set<Stats> findStatsByTournamentName(String tournamentName) throws ReadException {
        Set<Stats> stats = null;
        try{
            LOGGER.info("Finding stats by tournament name.");
            stats = new LinkedHashSet<Stats>(
                    em.createNamedQuery("findStatsByTournamentName")
                    .setParameter("tournamentName", tournamentName)
                    .getResultList()
            );
        }catch(Exception e){
            LOGGER.severe("Unexpected error occurred during finding stats by tournament name: "+e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return stats;
    }

    /**
     * 
     * @param stats
     * @throws CreateException 
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
     * 
     * @param stats
     * @throws UpdateException 
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
     * 
     * @param stats
     * @throws DeleteException 
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
