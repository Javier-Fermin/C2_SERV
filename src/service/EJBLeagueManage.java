/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import exceptions.*;
import java.entity.League;
import java.entity.Match;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 2dam
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
     * @param league
     * @throws CreateException
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
     * @param league
     * @throws UpdateException
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
     * @param league
     * @throws DeleteException
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
     * @param id
     * @return league
     * @throws FindException
     */
    @Override
    public League findOneLeague(Integer id) throws FindException {
        League league = null;
        try {
            LOGGER.info("LeagueManager: Finding league by id.");
            league = em.find(League.class, id);
            if (league != null) {
                LOGGER.log(Level.INFO, "LeagueManage: league", league.getId());
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception finding league:", e.getMessage());
            throw new FindException(e.getMessage());
        }
        return league;
    }

    /**
     * Find all Leagues
     *
     * @return leagues
     * @throws FindException
     */
    @Override
    public List<League> findAllLeagues() throws FindException {
        List<League> leagues = null;
        try {
            LOGGER.info("LeagueManager: Finding all league.");
            leagues = em.createNamedQuery("findAllLeagues").getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception finding leagues:", e.getMessage());
            throw new FindException(e.getMessage());

        }
        return leagues;
    }

    /**
     * Find league by name
     *
     * @param name
     * @return leagued
     * @throws FindException
     */
    @Override
    public League findLeagueByName(String name) throws FindException {
        League league = null;
        try {
            LOGGER.info("LeagueManager: Finding league by name.");
            league = (League) em.createNamedQuery("findLeagueByName").setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception finding league:", e.getMessage());
            throw new FindException(e.getMessage());
        }
        return league;
    }

    /**
     * find all finished leagues
     *
     * @param today
     * @return leagues
     * @throws FindException
     */
    @Override
    public List<League> findAllFinishLeagues(Date today) throws FindException {
        List<League> leagues = null;
        try {
            LOGGER.info("LeagueManager: Finding all finished leagues.");
            leagues = em.createNamedQuery("findAllFinishLeagues").setParameter("today", today).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception finding leagues:", e.getMessage());
            throw new FindException(e.getMessage());
        }
        return leagues;
    }

    /**
     * Find all unstarted leagues
     *
     * @param today
     * @return leagues
     * @throws FindException
     */
    @Override
    public List<League> findAllUnstartedLeagues(Date today) throws FindException {
        List<League> leagues = null;
        try {
            LOGGER.info("LeagueManager: Finding all unstarted leagues.");
            leagues = em.createNamedQuery("findAllUnstartedLeagues").setParameter("today", today).getResultList();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception finding leagues:", e.getMessage());
            throw new FindException(e.getMessage());
        }
        return leagues;
    }

    /**
     * Find league by match id
     *
     * @param id
     * @return league
     * @throws FindException
     */
    @Override
    public League findLeagueForMatch(Integer id) throws FindException {
        League league = null;
        try {
            LOGGER.info("LeagueManager: Finding league by id.");
            league = (League) em.createNamedQuery("findLeagueForMatch").setParameter("id", em.find(Match.class, id)).getSingleResult();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "LeagueManage: Exception finding league:", e.getMessage());
            throw new FindException(e.getMessage());
        }
        return league;
    }

}
