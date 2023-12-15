/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import ejb.TournamentLocalManagerEJB;
import entity.Tournament;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * RESTful web service class exposing CRUD operations for {@link Tournament} 
 * entities.
 * 
 * @author Fran
 */
@Path("tournaments") //"entities.Tournament")
public class TournamentREST {
    /**
     * Logger for class methods.
     */
    private static final Logger LOGGER =
            Logger.getLogger("javafxserverside");
    
    /**
     * EJB reference for business logic object.
     */
    @EJB
    private TournamentLocalManagerEJB ejbT;
    
    /**
     * RESTful POST method to create a {@link Tournament}object from XML 
     * representation, it throws InternalServerErrorException if a 
     * CreateException occurs.
     *
     * @param tournament the object containing the tournament data
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Tournament tournament){
        try{
            LOGGER.log(Level.INFO, "TournamentREST service: create.", tournament);
            ejbT.createTournament(tournament);
        }catch(CreateException ex){
            LOGGER.log(Level.SEVERE, "LeagueREST service: Exception creating league", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * RESTful PUT method to update a {@link Tournament} object from XML 
     * representation, it throws InternalServerErrorException if an 
     * UpdateException occurs.
     *
     * @param tournament the object containing the tournament data
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void update(Tournament tournament){
        try {
            LOGGER.log(Level.INFO, "TournamentREST service: update.", tournament);
            ejbT.updateTournament(tournament);
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE, "TournamentREST service: Exception updating league", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * RESTful DELETE method to remove a {@link Tournament} object from id,
     * it throws InternalServerErrorException if a DeleteException or a 
     * ReadException occurs.
     *
     * @param id the id used to find a tournament
     */
    
    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id){
        try{
            LOGGER.log(Level.INFO, "TournamentREST service: delete.", id);
            ejbT.deleteTournament(ejbT.findTournamentById(id));
        }catch(DeleteException ex){
            LOGGER.log(Level.SEVERE, "TournamentREST service: Exception deleting tournament", ex.getMessage());
            throw new InternalServerErrorException(ex);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE, "TournamentREST service: Exception finding the tournament, {0}.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }
    
    /**
     * RESTful GET method for finding a {@link Tournament} object by id.
     * It throws InternalServerErrorException if a ReadException occurs.
     * 
     * @param id The id for the object to be read.
     * @return A Tournament object containing data. 
     */
    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Tournament findTournamentById(@PathParam("id") Integer id){
        Tournament tournament = null;
        
        try{
            LOGGER.log(Level.INFO, "TournamentREST service: find tournament by id.", id);
            tournament=ejbT.findTournamentById(id);
        }catch(ReadException ex){
            LOGGER.log(Level.SEVERE, "TournamentREST service: Exception finding any tournament, {0}.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        
        return tournament;
    }
    
    /**
     * RESTful GET method for reading all {@link Tournament} objects.
     * It throws InternalServerErrorException if a ReadException occurs.
     * 
     * @return A List of Tournament objects containing data. 
     */
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tournament> findAllTournaments(){
        List<Tournament> tournaments=null;
        
        try{
            LOGGER.log(Level.INFO, "TournamentREST service: find all tournaments.");
            tournaments=ejbT.findAllTournaments();
        }catch(ReadException ex){
            LOGGER.log(Level.SEVERE, "TournamentREST service: Exception reading all tournaments, {0}.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        
        return tournaments;
    }
    
    /**
     * RESTful GET method for finding {@link Tournament} objects by name.
     * It throws InternalServerErrorException if a ReadException occurs.
     * 
     * @param name The name for the object to be read.
     * @return A List of Tournament objects containing data. 
     */
    @GET
    @Path("{name}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tournament> findTournamentByName(@PathParam("name") String name){
        List<Tournament> tournaments=null;
        
        try{
            LOGGER.log(Level.INFO, "TournamentREST service: find tournaments by name contains {0}.",name);
            tournaments=ejbT.findTournamentsByName(name);
        }catch(ReadException ex){
            LOGGER.log(Level.SEVERE, "TournamentREST service: Exception finding any tournament, {0}.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        
        return tournaments;
    }
    /**
     * RESTful GET method for finding {@link Tournament} objects by format.
     * It throws InternalServerErrorException if a ReadException occurs.
     * 
     * @param bestOf The format for the object to be read.
     * @return A List of Tournament objects containing data. 
     */
    @GET
    @Path("bestOf/{bestOf}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tournament> findTournamentByFormat(@PathParam("bestOf") Integer bestOf){
        List<Tournament> tournaments=null;
        
        try{
            LOGGER.log(Level.INFO, "TournamentREST service: find tournaments by bestOf = {0}.",bestOf);
            tournaments=ejbT.findTournamentsByFormat(bestOf);
        }catch(ReadException ex){
            LOGGER.log(Level.SEVERE, "TournamentREST service: Exception finding any tournament, {0}.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        
        return tournaments;
    }
    /**
     * RESTful GET method for finding {@link Tournament} objects by date.
     * It throws InternalServerErrorException if a ReadException occurs.
     * 
     * @param date The date for the object to be read.
     * @return A List of Tournament objects containing data. 
     */
    @GET
    @Path("date/{date}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Tournament> findTournamentByDate(@PathParam("date") Date date){
        List<Tournament> tournaments=null;
        
        try{
            LOGGER.log(Level.INFO, "TournamentREST service: find tournaments by date = {0}.",date);
            tournaments=ejbT.findTournamentsByDate(date);
        }catch(ReadException ex){
            LOGGER.log(Level.SEVERE, "TournamentREST service: Exception finding any tournament, {0}.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        
        return tournaments;
    }
    
    /**
     * RESTful GET method for finding a {@link Tournament} object by a 
     * {@link Match} object.
     * It throws InternalServerErrorException if a ReadException occurs.
     * 
     * @param id The id for the object to be read.
     * @return A Tournament object containing data. 
     */
    @GET
    @Path("match/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Tournament findTournamentByMatch(@PathParam("id") Integer id){
        Tournament tournament = null;
        
        try{
            LOGGER.log(Level.INFO, "TournamentREST service: find tournament by match.id = {0}.", id);
            tournament=ejbT.findTournamentById(id);
            //tournament=ejbT.findMatchTournament(ejbM.findMatchById(id));
        }catch(ReadException ex){
            LOGGER.log(Level.SEVERE, "TournamentREST service: Exception finding any tournament, {0}.", ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        
        return tournament;
    }
}
