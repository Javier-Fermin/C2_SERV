/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author 2dam
 */
public class FindException extends Exception{
    
     /**
     * Creates a new instance of <code>CreateException</code> without detail message.
     */
    public FindException() {
    }


    /**
     * Constructs an instance of <code>CreateException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public FindException(String msg) {
        super(msg);
    }
    
    
}
