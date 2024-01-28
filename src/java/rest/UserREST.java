/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import cryptography.AsymetricServer;
import cryptography.SymmetricCryptography;
import ejb.UserManagerLocal;
import entity.Player;
import entity.User;
import exception.CreateException;
import exception.DeleteException;
import exception.ReadException;
import exception.UpdateException;
import static java.lang.Math.random;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author imanol
 */
@Path("entities.user")
public class UserREST {

    /**
     * Logger for class methods.
     */
    private static final Logger LOGGER
            = Logger.getLogger("javafxserverside");
    /**
     * EJB reference for business logic object.
     */
    @EJB
    private UserManagerLocal ejb;

    /**
     * RESTful POST method for creating {@link User} objects from XML
     * representation.
     *
     * @param user The object containing user data.
     */
    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createUser(User user) {
        try {
            LOGGER.log(Level.INFO, "UserRESTful service: create {0}.", user);
            user.setPasswd(AsymetricServer.hashText(AsymetricServer.decryptData(user.getPasswd())));
            ejb.createUser(user);
        } catch (CreateException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception creating user, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * RESTful PUT method for updating {@link User} objects from XML
     * representation.
     *
     * @param user The object containing user data.
     */
    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateUser(User user) {
        try {
            LOGGER.log(Level.INFO, "UserRESTful service: update {0}.", user);
            ejb.updateUser(user);
        } catch (UpdateException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception updating user, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    /**
     * RESTful DELETE method for deleting {@link User} objects from id.
     *
     * @param id The id for the object to be deleted.
     */
    @DELETE
    @Path("{id}")
    //@Consumes({"application/xml", "application/json"})
    public void deleteUser(@PathParam("id") Integer id) {
        try {
            LOGGER.log(Level.INFO, "UserRESTful service: delete User by id={0}.", id);
            ejb.deleteUser(ejb.findUserById(id));
        } catch (ReadException | DeleteException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception deleting user by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findAllUsers() {
        List<User> users = null;
        try {
            LOGGER.log(Level.INFO, "UserRESTful service: find all Users.");
            users = ejb.findUsers();
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception reading all Users, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return users;
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public User findUserById(@PathParam("id") Integer id) {
        User user = null;
        try {
            LOGGER.log(Level.INFO, "UserRESTful service: find User by id={0}.", id);
            user = ejb.findUserById(id);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception reading user by id, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return user;

    }

    /**
     * 
     * 
     * @param email
     * @return 
     */
    @GET
    @Path("email/{email}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> findUserByEmail(@PathParam("email") String email) {
        List<User> users = null;
        try {
            LOGGER.log(Level.INFO, "UserRESTful service: find User by email={0}.", email);
            users = ejb.findUserByMail(email);
        } catch (ReadException ex) {
            LOGGER.log(Level.SEVERE,
                    "UserRESTful service: Exception reading user by email, {0}",
                    ex.getMessage());
            throw new InternalServerErrorException(ex);
        }
        return users;
    }
    
    /**
     * RESTful POST method for LogIn a {@link User}.
     *
     * @param user The user asking for log in.
     */
    @POST
    @Path("logIn")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<User> logIn(User user) {
        LOGGER.info("UserRESTful service: LogIn user: "+user.getEmail());
        List<User> users = null;
        try {
            user.setPasswd(AsymetricServer.hashText(AsymetricServer.decryptData(user.getPasswd())));
            
            users = ejb.findUserByMail(user.getEmail());
            if(users.isEmpty()){
                LOGGER.severe("UserRESTful service: No user found for LogIn");
                throw new NotFoundException("No user found for the given credentials");
            }
            if(users.get(0).getPasswd().equals(user.getPasswd())){
                LOGGER.info("UserRESTful service: user found");
                return users;
            }else{
                LOGGER.severe("UserRESTful service: No user found for LogIn");
                throw new NotFoundException("No user found for the given credentials");
            }
        } catch (ReadException ex) {
            LOGGER.severe("UserRESTful service: "+ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }
    
    /**
     * This method sends a mail with a new generated password for the user
     * 
     * @param email The email of the user that is asking for the password
     */
    @GET
    @Path("recoverPassword/{email}")
    public void recoverPassword(@PathParam("email") String email){
        LOGGER.info("UserRESTful service: Password recovery requested.");
        SymmetricCryptography symCryp = new SymmetricCryptography();
        LOGGER.info("UserRESTful service: Getting credentials for mail.");
        String[] credentials = symCryp.descifrarTexto("uwu").split("/");
        LOGGER.info("UserRESTful service: Getting properties.");
        Properties properties = System.getProperties(); 
        LOGGER.info("UserRESTful service: Setting necessaries properties.");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        LOGGER.info("UserRESTful service: Getting a session for the mail.");
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(credentials[0], credentials[1]);
            }
        });
        try {
            LOGGER.info("UserRESTful service: Preparing the mail");
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(credentials[0]));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            LOGGER.info("UserRESTful service: Generating new password.");
            int leftLimit = 48;
            int rightLimit = 122;
            int targetStringLength = 10;
            Random random = new Random();
            String generatedUserPasswd = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
            LOGGER.info("UserRESTful service: Completing te mail");
            message.setSubject("PASSWORD RESET");
            message.setText("You have asked for a password reset, here is your new password:\n"
                    + generatedUserPasswd);
            LOGGER.info("UserRESTful service: Sending the mail...");
            Transport.send(message);
            LOGGER.info("UserRESTful service: Mail sent.");
            ejb.recoverPassword(generatedUserPasswd, email);
        } catch (MessagingException | ReadException e) {
            LOGGER.severe("UserRESTful service: "+e.getMessage());
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
