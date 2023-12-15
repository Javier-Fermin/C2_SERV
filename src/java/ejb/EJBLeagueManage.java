/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import exception.*;
import entity.League;
import entity.Match;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB class for League entity
 *
 * @author Emil
 */
@Stateless(name = "EJBLeagueManage")
public class EJBLeagueManage implements LeagueManageLocal {

    /**
     * Entity Manager for C2PU persistance unit.
     */
    @PersistenceContext(unitName = "C2PU")
    private EntityManager em;

    private static final Logger LOGGER = Logger.getLogger(EJBLeagueManage.class.getName());

    /**
     * Create a league
     *
     * @param league to create the league
     * @throws CreateException if have any errors
     */
    @Override
    public void createLeague(League league) throws CreateException {
        try {
            em.persist(league);
            LOGGER.info("LeagueManage: league cerated.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception creating league:", e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }

    /**
     * Update a league
     *
     * @param league to update
     * @throws UpdateException if have any errors
     */
    @Override
    public void updateLeague(League league) throws UpdateException {
        try {
            if (!em.contains(league)) {
                em.merge(league);
            }
            em.flush();
            LOGGER.info("LeagueManage: league Updated.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception updating league:", e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }

    /**
     * Delete a League
     *
     * @param league to delete
     * @throws DeleteException if have any errors
     */
    @Override
    public void deleteLeague(League league) throws DeleteException {
        try {
            em.remove(league);
            LOGGER.info("LeagueManage: league deleted.");
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception deleting league:", e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }

    /**
     * Find a league by id
     *
     * @param id to find the league
     * @return league finded by id
     * @throws ReadException if have any errors
     */
    @Override
    public League findOneLeague(Integer id) throws ReadException {
        League league = null;
        try {
            LOGGER.info("LeagueManager: Finding league by id.");
            league = em.find(League.class, id);
            if (league != null) {
                LOGGER.log(Level.INFO, "LeagueManage: league", league.getId());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception finding league:", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return league;
    }

    /**
     * Find all Leagues
     *
     * @return leagues finded
     * @throws ReadException if have any errors
     */
    @Override
    public List<League> findAllLeagues() throws ReadException {
        List<League> leagues = null;
        try {
            LOGGER.info("LeagueManager: Finding all league.");
            leagues = em.createNamedQuery("findAllLeagues").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception finding leagues:", e.getMessage());
            throw new ReadException(e.getMessage());

        }
        return leagues;
    }

    /**
     * Find league by name
     *
     * @param name to find the league
     * @return league finded by the name
     * @throws ReadException if have any errors
     */
    @Override
    public League findLeagueByName(String name) throws ReadException {
        League league = null;
        try {
            LOGGER.info("LeagueManager: Finding league by name.");
            league = (League) em.createNamedQuery("findLeagueByName").setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception finding league:", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return league;
    }

    /**
     * find all finished leagues
     *
     * @param today to compare with the end date
     * @return leagues finished
     * @throws ReadException if have any errors
     */
    @Override
    public List<League> findAllFinishLeagues(Date today) throws ReadException {
        List<League> leagues = null;
        try {
            LOGGER.info("LeagueManager: Finding all finished leagues.");
            leagues = em.createNamedQuery("findAllFinishLeagues").setParameter("today", today).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception finding leagues:", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return leagues;
    }

    /**
     * Find all unstarted leagues
     *
     * @param today to compare with the end date
     * @return leagues unstarted
     * @throws ReadException if have any errors
     */
    @Override
    public List<League> findAllUnstartedLeagues(Date today) throws ReadException {
        List<League> leagues = null;
        try {
            LOGGER.info("LeagueManager: Finding all unstarted leagues.");
            leagues = em.createNamedQuery("findAllUnstartedLeagues").setParameter("today", today).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception finding leagues:", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return leagues;
    }

    /**
     * Find league by match id
     *
     * @param id to find the league of the match
     * @return league by the match id
     * @throws ReadException if have any errors
     */
    @Override
    public League findLeagueForMatch(Integer id) throws ReadException {
        League league = null;
        try {
            LOGGER.info("LeagueManager: Finding league by id.");
            league = (League) em.createNamedQuery("findLeagueForMatch").setParameter("id", em.find(Match.class, id)).getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception finding league:", e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return league;
    }

}
