/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;

    /**
     * Creates a new instance of <code>DeleteExceptopn</code> without detail
     * message.
     * @author 2dam
     */
public class DeleteException extends Exception{
    
     /**
     * Creates a new instance of <code>CreateException</code> without detail message.
     */
    public DeleteException() {
    }

    /**
     * Constructs an instance of <code>DeleteExceptopn</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public DeleteException(String msg) {
        super(msg);
    }
}
