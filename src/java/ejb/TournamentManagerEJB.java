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
import entity.Match;
import entity.Tournament;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;

/**
 * EJB class for managing Tournament entity CRUD operations
 * @author Fran
 */
@Stateless
public class TournamentManagerEJB implements TournamentLocalManagerEJB{
    /**
     * Logger for the class.
     */
    private static final Logger LOG = Logger.getLogger(TournamentManagerEJB.class.getName());

    /**
     * Entity manager object.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Finds a List of {@link Tournament} objects by its name. 
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
            LOG.log(Level.SEVERE, "TournamentManager: Exception Finding tournament by name:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return tournaments;
    }
    /**
     * Finds a List of {@link Tournament} objects by its date of creation. 
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
            LOG.log(Level.SEVERE, "TournamentManager: Exception Finding tournament by date:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return tournaments;
    }
    /**
     * Finds a List of {@link Tournament} objects by its play format. 
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
            LOG.log(Level.SEVERE, "TournamentManager: Exception Finding tournament by format:",
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
            tournament=(Tournament) em.createNamedQuery("findMatchTournament").setParameter("id", match.getId()).getSingleResult();
        }catch(Exception e){
            LOG.log(Level.SEVERE, "TournamentManager: Exception Finding tournament by match:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return tournament;
    }
    /**
     * Finds a {@link Tournament} by its id. 
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
            LOG.log(Level.SEVERE, "TournamentManager: Exception Finding tournament by id:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return tournament;
    }
    /**
     * Finds a List of {@link Tournament} objects containing data for all 
     * tournaments in the application data storage.
     * @return A List of {@link Tournament} objects.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public List<Tournament> findAllTournaments() throws ReadException{
        List<Tournament> tournaments=null;
        try{
            LOG.info("TournamentManager: Reading all tournaments.");
            tournaments=em.createNamedQuery("findAllTournaments").getResultList();
        }catch(Exception e){
            LOG.log(Level.SEVERE, "TournamentManager: Exception reading all tournaments:",
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
        LOG.info("TournamentManager: Creating tournament.");
        try{
            em.persist(tournament);
            LOG.info("TournamentManager: Tournament created.");
        }catch(Exception e){
            LOG.log(Level.SEVERE, "TournamentManager: Exception creating tournament.{0}",
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
            LOG.log(Level.SEVERE, "TournamentManager: Exception Updating tournament.{0}",
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
        LOG.info("TournamentManager: Deleting tournament.");
        try{
            tournament=em.merge(tournament);
            em.remove(tournament);
            LOG.info("TournamentManager: Tournament deleted.");
        }catch(Exception e){
            LOG.log(Level.SEVERE, "TournamentManager: Exception deleting tournament.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }
    
    /***
     * Takes a Match out of the collection of matches of Tournament
     * @param match disinherited Tournament match
     * @param tournament The tournament the match belonged to
     * @throws DeleteException If there is any Exception during processing
     */
    
    public void setApartMatch(Match match, Tournament tournament) throws DeleteException{
        tournament.getMatches().remove(match);
    }
    
}
