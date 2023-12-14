/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.ejb;

import java.exception.CreateException;
import java.exception.DeleteException;
import java.exception.ReadException;
import java.exception.UpdateException;
import java.entity.Sponsor;
import java.util.List;
import javax.ejb.Local;

/**
 * EJB Local Interface for managing Sponsor entity CRUD operations
 * @author Fran
 */
@Local
public interface SponsorLocalManagerEJB {
    /**
     * Finds a {@link Sponsor} by its id. 
     * @param id The id for a sponsor to be found.
     * @return The {@link Sponsor} object containing sponsor data. 
     * @throws ReadException If there is any Exception during processing.
     */
    public Sponsor findSponsorById(int id) throws ReadException;
    /**
     * Finds a List of {@link Sponsor} objects containing data for all sponsrs 
     * in the application data storage.
     * @return A List of {@link Sponsor} objects.
     * @throws ReadException If there is any Exception during processing.
     */
    public List<Sponsor> findAllSponsors() throws ReadException;
    /**
     * Creates a Sponsor and stores it in the underlying application storage. 
     * @param sponsor The {@link Sponsor} object containing the sponsor data. 
     * @throws CreateException If there is any Exception during processing.
     */
    public void createSponsor(Sponsor sponsor) throws CreateException;
    /**
     * Updates a Sponsor and stores it in the underlying application storage. 
     * @param sponsor The {@link Sponsor} object containing the sponsor data. 
     * @throws UpdateException If there is any Exception during processing.
     */
    public void updateSponsor(Sponsor sponsor) throws UpdateException;
    /**
     * Deletes a Sponsor and stores it in the underlying application storage. 
     * @param sponsor The {@link Sponsor} object containing the sponsor data. 
     * @throws DeleteException If there is any Exception during processing.
     */
    public void deleteSponsor(Sponsor sponsor) throws DeleteException;
}
