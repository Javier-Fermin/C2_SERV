/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import ejb.SponsorLocalManagerEJB;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import entity.Sponsor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * EJB class for managing Sponsor entity CRUD operations
 * @author Fran
 */
public class SponsorManagerEJB implements SponsorLocalManagerEJB{
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
     * Finds a {@link Sponsor} by its id. 
     * @param id The id for a sponsor to be found.
     * @return The {@link Sponsor} object containing sponsor data. 
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public Sponsor findSponsorById(int id) throws ReadException {
        Sponsor sponsor=null;
        try{
            LOG.info("SponsorManager: Reading sponsor by id.");
            sponsor=em.find(Sponsor.class, id);
        }catch(Exception e){
            LOG.log(Level.SEVERE, "SponsorManager: Exception Finding sponsor by id:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return sponsor;
    }
    
    /**
     * Finds a List of {@link Sponsor} objects containing data for all sponsrs 
     * in the application data storage.
     * @return A List of {@link Sponsor} objects.
     * @throws ReadException If there is any Exception during processing.
     */
    @Override
    public List<Sponsor> findAllSponsors() throws ReadException {
        List<Sponsor> sponsors=null;
        try{
            LOG.info("SponsorManager: Reading all sponsors.");
            sponsors=em.createNamedQuery("findAllSponsors").getResultList();
        }catch(Exception e){
            LOG.log(Level.SEVERE, "SponsorManager: Exception Finding sponsors:",
                    e.getMessage());
            throw new ReadException(e.getMessage());
        }
        return sponsors;
    }
    
    /**
     * Creates a Sponsor and stores it in the underlying application storage. 
     * @param sponsor The {@link Sponsor} object containing the sponsor data. 
     * @throws CreateException If there is any Exception during processing.
     */
    @Override
    public void createSponsor(Sponsor sponsor) throws CreateException {
        LOG.info("SponsorManager: Creating sponsor.");
        try{
            em.persist(sponsor);
            LOG.info("SponsorManager: Sponsor created.");
        }catch(Exception e){
            LOG.log(Level.SEVERE, "SponsorManager: Exception creating sponsor.{0}",
                    e.getMessage());
            throw new CreateException(e.getMessage());
        }
    }
    
    /**
     * Updates a Sponsor and stores it in the underlying application storage. 
     * @param sponsor The {@link Sponsor} object containing the sponsor data. 
     * @throws UpdateException If there is any Exception during processing.
     */
    @Override
    public void updateSponsor(Sponsor sponsor) throws UpdateException {
        LOG.info("SponsorManager: Updating sponsor.");
        try{
            em.merge(sponsor);
            em.flush();
            LOG.info("SponsorManager: Sponsor updated.");
        }catch(Exception e){
            LOG.log(Level.SEVERE, "SponsorManager: Exception updating sponsor.{0}",
                    e.getMessage());
            throw new UpdateException(e.getMessage());
        }
    }
    
    /**
     * Deletes a Sponsor and stores it in the underlying application storage. 
     * @param sponsor The {@link Sponsor} object containing the sponsor data. 
     * @throws DeleteException If there is any Exception during processing.
     */
    @Override
    public void deleteSponsor(Sponsor sponsor) throws DeleteException {
        LOG.info("SponsorManager: Deleting sponsor.");
        try{
            sponsor=em.merge(sponsor);
            em.remove(sponsor);
            LOG.info("SponsorManager: Sponsor deleted.");
        }catch(Exception e){
            LOG.log(Level.SEVERE, "SponsorManager: Exception deleting sponsor.{0}",
                    e.getMessage());
            throw new DeleteException(e.getMessage());
        }
    }
    
}
