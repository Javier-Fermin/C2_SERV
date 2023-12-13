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
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author javie
 */
@Local
public interface StatsManagerEJBLocal {
    /**
     * 
     * @param id
     * @return
     * @throws ReadException 
     */
    public Stats findStatById(Integer id) throws ReadException;
    
    /**
     * 
     * @return
     * @throws ReadException 
     */
    public Set<Stats> findAllStats() throws ReadException;
    
    /**
     * 
     * @param nickname
     * @return
     * @throws ReadException 
     */
    public Set<Stats> findStatsByPlayerNickname(String nickname) throws ReadException;
    
    /**
     * 
     * @param matchId
     * @return
     * @throws ReadException 
     */
    public Set<Stats> findStatsByMatchId(Integer matchId) throws ReadException;
    
    /**
     * 
     * @param leagueName
     * @return
     * @throws ReadException 
     */
    public Set<Stats> findStatsByLeagueName(String leagueName) throws ReadException;
    
    /**
     * 
     * @param tournamentName
     * @return
     * @throws ReadException 
     */
    public Set<Stats> findStatsByTournamentName(String tournamentName) throws ReadException;
    
    /**
     * 
     * @param stats
     * @throws CreateException 
     */
    public void createStats(Stats stats) throws CreateException;
    
    /**
     * 
     * @param stats
     * @throws UpdateException 
     */
    public void updateStats(Stats stats) throws UpdateException;
    
    /**
     * 
     * @param stats
     * @throws DeleteException 
     */
    public void deleteStats(Stats stats) throws DeleteException;
}
