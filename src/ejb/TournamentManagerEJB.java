/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import java.entity.Match;
import java.entity.Tournament;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Fran
 */
public class TournamentManagerEJB implements TournamentLocalManagerEJB{

    private static final Logger LOG = Logger.getLogger(TournamentManagerEJB.class.getName());

    
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Finds one or many {@link Tournament} by its name. 
     * @param name The name for a tournament to be found.
     * @return The {@link Tournament} object containing tournament data. 
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public List<Tournament> findTournamentsByName(String name) throws ReadException{
        List<Tournament> tournaments=null;
        try{
            LOG.info("TournamentManager: Reading tournaments by name.");
            tournaments=em.createNamedQuery("findTournamentsByName").setParameter("n", '%'+name+'%').getResultList();
        }catch(Exception e){
            LOG.log(Level.SEVERE, "UserManager: Exception Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return tournaments;
    }
    /**
     * Finds one or many {@link Tournament} by its date of creation. 
     * @param date The date for a tournament to be found.
     * @return The {@link Tournament} object containing tournament data. 
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public List<Tournament> findTournamentsByDate(Date date) throws ReadException{
        List<Tournament> tournaments=null;
        try{
            LOG.info("TournamentManager: Reading tournaments by date.");
            tournaments=em.createNamedQuery("findTournamentsByDate").setParameter("date", date).getResultList();
        }catch(Exception e){
            LOG.log(Level.SEVERE, "UserManager: Exception Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return tournaments;
    }
    /**
     * Finds one or many {@link Tournament} by its play format. 
     * @param bestOf The format for a tournament to be found.
     * @return The List object full of {@link Tournament} object containing tournament data. 
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public List<Tournament> findTournamentsByFormat(int bestOf) throws ReadException{
        List<Tournament> tournaments=null;
        try{
            LOG.info("TournamentManager: Reading tournaments by format.");
            tournaments=em.createNamedQuery("findTournamentsByFormat").setParameter("bestOf", bestOf).getResultList();
        }catch(Exception e){
            LOG.log(Level.SEVERE, "UserManager: Exception Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return tournaments;
    }
    /**
     * Finds a {@link Tournament} of a selected match. 
     * @param match The match taken as reference for a tournament to be found.
     * @return The {@link Tournament} object containing tournament data. 
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public Tournament findMatchTournament(Match match) throws ReadException{
        Tournament tournament=null;
        try{
            LOG.info("TournamentManager: Reading the selected match's tournament.");
            tournament=(Tournament) em.createNamedQuery("findMatchTournaments").setParameter("id", match.getId()).getSingleResult();
        }catch(Exception e){
            LOG.log(Level.SEVERE, "UserManager: Exception Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return tournament;
    }
    /**
     * Finds one or many {@link Tournament} by its id. 
     * @param id The id for a tournament to be found.
     * @return The {@link Tournament} object containing tournament data. 
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public Tournament findTournamentById(int id) throws ReadException{
        Tournament tournament=null;
        try{
            LOG.info("TournamentManager: Reading tournament by id.");
            tournament=em.find(Tournament.class, id);
        }catch(Exception e){
            LOG.log(Level.SEVERE, "UserManager: Exception Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return tournament;
    }
    /**
     * Finds a List of {@link User} objects containing data for all users in the
     * application data storage.
     * @return A List of {@link User} objects.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public List<Tournament> findAllTournaments() throws ReadException{
        List<Tournament> tournaments=null;
        try{
            LOG.info("TournamentManager: Reading all tournaments.");
            tournaments=em.createNamedQuery("findAllTournaments").getResultList();
        }catch(Exception e){
            LOG.log(Level.SEVERE, "UserManager: Exception Finding user by login:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return tournaments;
    }
    /**
     * Creates a Tournament and stores it in the underlying application storage. 
     * @param tournament The {@link Tournament} object containing the tournament data. 
     * @throws CreateException If there is any Exception during processing.
     */
    @Override
    public void createTournament(Tournament tournament) throws CreateException{
        LOG.info("");
        try{
            em.persist(tournament);
            LOG.info("");
        }catch(Exception e){
            LOG.log(Level.SEVERE, "UserManager: Exception Finding user by login:",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }
    /**
     * Updates a tournament's data in the underlying application storage. 
     * @param tournament The {@link Tournament }object containing the tournament data. 
     * @throws UpdateException If there is any Exception during processing.
     */
    @Override
    public void updateTournament(Tournament tournament) throws UpdateException{
        LOG.info("TournamentManager: Updating tournament.");
        try{
            em.merge(tournament);
            em.flush();
            LOG.info("TournamentManager: Tournament updated.");
        }catch(Exception e){
            LOG.log(Level.SEVERE, "UserManager: Exception Finding user by login:",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }
    /**
     * Deletes a tournament's data in the underlying application storage. 
     * @param tournament The {@link Tournament} object containing the tournament data. 
     * @throws DeleteException If there is any Exception during processing.
     */
    @Override
    public void deleteTournament(Tournament tournament) throws DeleteException{
        LOG.info("TournamentManager: Updating tournament.");
        try{
            tournament=em.merge(tournament);
            em.remove(tournament);
            LOG.info("TournamentManager: Tournament updated.");
        }catch(Exception e){
            LOG.log(Level.SEVERE, "UserManager: Exception Finding user by login:",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }
    
}
