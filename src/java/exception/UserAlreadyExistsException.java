package exception;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * An UserAlreadyExistsException is thrown when the user that is trying to signUp
 * already exists in the database
 * 
 * @author Javier, Fran, Imanol, Emil
 */
public class UserAlreadyExistsException extends Exception{
    /**
     * Creates a new instance of <code>UserAlreadyExistsException</code> without detail
     * message.
     */
    public UserAlreadyExistsException() {
    }

    /**
     * Constructs an instance of <code>UserAlreadyExistsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UserAlreadyExistsException(String msg) {
        super(msg);
    }
}
