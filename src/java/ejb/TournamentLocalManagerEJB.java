/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Match;
import entity.Tournament;
import java.util.Date;
import java.util.List;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import javax.ejb.Local;
/**
 *  EJB Local Interface for managing Tournament entity CRUD operations
 * @author Fran
 */
@Local
public interface TournamentLocalManagerEJB {
    /**
     * Finds a List of {@link Tournament} objects by its name. 
     * @param name The name for a tournament to be found.
     * @return The {@link Tournament} object containing tournament data. 
     * @throws ReadException If there is any Exception during processing.
     */
    public List<Tournament> findTournamentsByName(String name) throws ReadException;
     /**
     * Finds a List of {@link Tournament} objects by its date of creation. 
     * @param date The date for a tournament to be found.
     * @return The {@link Tournament} object containing tournament data. 
     * @throws ReadException If there is any Exception during processing.
     */
    public List<Tournament> findTournamentsByDate(Date date) throws ReadException;
    /**
     * Finds a List of {@link Tournament} objects by its play format. 
     * @param bestOf The format for a tournament to be found.
     * @return The List object full of {@link Tournament} object containing tournament data. 
     * @throws ReadException If there is any Exception during processing.
     */
    public List<Tournament> findTournamentsByFormat(int bestOf) throws ReadException;
    /**
     * Finds a {@link Tournament} of a selected match. 
     * @param match The match taken as reference for a tournament to be found.
     * @return The {@link Tournament} object containing tournament data. 
     * @throws ReadException If there is any Exception during processing.
     */
    public Tournament findMatchTournament(Match match) throws ReadException;
    /**
     * Finds a {@link Tournament} by its id. 
     * @param id The id for a tournament to be found.
     * @return The {@link Tournament} object containing tournament data. 
     * @throws ReadException If there is any Exception during processing.
     */
    public Tournament findTournamentById(int id) throws ReadException;
    /**
     * Finds a List of {@link Tournament} objects containing data for all 
     * tournaments in the application data storage.
     * @return A List of {@link Tournament} objects.
     * @throws ReadException If there is any Exception during processing.
     */
    public List<Tournament> findAllTournaments() throws ReadException;
    /**
     * Creates a Tournament and stores it in the underlying application storage. 
     * @param tournament The {@link Tournament} object containing the tournament data. 
     * @throws CreateException If there is any Exception during processing.
     */
    public void createTournament(Tournament tournament) throws CreateException;
    /**
     * Updates a tournament's data in the underlying application storage. 
     * @param tournament The {@link Tournament }object containing the tournament data. 
     * @throws UpdateException If there is any Exception during processing.
     */
    public void updateTournament(Tournament tournament) throws UpdateException;
    /**
     * Deletes a tournament's data in the underlying application storage. 
     * @param tournament The {@link Tournament} object containing the tournament data. 
     * @throws DeleteException If there is any Exception during processing.
     */
    public void deleteTournament(Tournament tournament) throws DeleteException;
    /***
     * Takes a Match out of the collection of matches of Tournament
     * @param match disinherited Tournament match
     * @param tournament The tournament the match belonged to
     * @throws DeleteException If there is any Exception during processing
     */
    public void setApartMatch(Match match, Tournament tournament) throws DeleteException;
}
