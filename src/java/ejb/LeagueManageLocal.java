/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import exception.DeleteException;
import exception.UpdateException;
import exception.ReadException;
import exception.CreateException;
import entity.League;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 * Local Interface for League Entity
 * 
 * @author Emil
 */
@Local
public interface LeagueManageLocal {
    
    /**
     * Create a new League, throws CreateException in case of error
     * 
     * @param league to create the league
     * @throws CreateException if have any errors
     */
    public void createLeague(League league) throws CreateException;
    
    /**
     * Update one League, throws UpdateException in case of error
     * 
     * @param league to update
     * @throws UpdateException if have any errors
     */
    public void updateLeague(League league) throws UpdateException;
    
    /**
     * Delete one League, throws DeleteException in case of error
     * 
      * @param league to delete
     * @throws DeleteException if have any errors
     */
    public void deleteLeague(League league) throws DeleteException;
    
    /**
     * Find one League by leagues id, throws ReadException in case of error
     * 
     * @param id to find the league
     * @return league finded by id
     * @throws ReadException if have any errors
     */
    public League findOneLeague(Integer id) throws ReadException;
    
    /**
     * Find a list of all leagues, throws ReadException in case of error
     * 
     * @return leagues finded
     * @throws ReadException if have any errors
     */
    public List<League> findAllLeagues() throws ReadException;
    
    /**
     * Find one League by leagues name, throws ReadException in case of error
     * 
     * @param name to find the league
     * @return league finded by the name
     * @throws ReadException if have any errors
     */
    public List<League> findLeagueByName(String name) throws ReadException;
    
    /**
     * Find all finished leagues taking locals date, throws ReadException in case of error
     * 
     * @param today to compare with the end date
     * @return leagues finished
     * @throws ReadException if have any errors
     */
    public List<League> findAllFinishLeagues(Date today) throws ReadException;

    /**
     * find all unstarted leagues taking locals date, throws ReadException in case of error
     * 
     * @param today to compare with the end date
     * @return leagues unstarted
     * @throws ReadException if have any errors
     */
    public List<League> findAllUnstartedLeagues(Date today) throws ReadException;
    
    /**
     * Find a league by a match id, throws ReadException in case of error
     * 
     * @param id to find the league of the match
     * @return league by the match id
     * @throws ReadException if have any errors
     */
    public List<League> findLeagueForMatch(Integer id) throws ReadException;
    
}
