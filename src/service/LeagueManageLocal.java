/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import exceptions.*;
import java.entity.League;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 2dam
 */
@Local
public interface LeagueManageLocal {
    
    /**
     * Create a new League, throws CreateException in case of error
     * 
     * @param league
     * @throws CreateException 
     */
    public void createLeague(League league) throws CreateException;
    
    /**
     * Update one League, throws UpdateException in case of error
     * 
     * @param league
     * @throws UpdateException 
     */
    public void updateLeague(League league) throws UpdateException;
    
    /**
     * Delete one League, throws DeleteException in case of error
     * 
     * @param league
     * @throws DeleteException 
     */
    public void deleteLeague(League league) throws DeleteException;
    
    /**
     * Find one League by leagues id, throws FindException in case of error
     * 
     * @param id
     * @return league
     * @throws FindException 
     */
    public League findOneLeague(Integer id) throws FindException;
    
    /**
     * Find a list of all leagues, throws FindException in case of error
     * 
     * @return leagues
     * @throws FindException 
     */
    public List<League> findAllLeagues() throws FindException;
    
    /**
     * Find one League by leagues name, throws FindException in case of error
     * 
     * @param name
     * @return league
     * @throws FindException 
     */
    public League findLeagueByName(String name) throws FindException;
    
    /**
     * Find all finished leagues taking locals date, throws FindException in case of error
     * 
     * @param today
     * @return leagues
     * @throws FindException 
     */
    public List<League> findAllFinishLeagues(Date today) throws FindException;

    /**
     * find all unstarted leagues taking locals date, throws FindException in case of error
     * 
     * @param today
     * @return leagues
     * @throws FindException 
     */
    public List<League> findAllUnstartedLeagues(Date today) throws FindException;
    
    /**
     * Find a league by a match id, throws FindException in case of error
     * 
     * @param id
     * @return league
     * @throws FindException 
     */
    public League findLeagueForMatch(Integer id) throws FindException;
    
}
