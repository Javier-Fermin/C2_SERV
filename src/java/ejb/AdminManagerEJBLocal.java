/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import entity.Admin;
import exceptions.CreateException;
import exceptions.DeleteException;
import exceptions.ReadException;
import exceptions.UpdateException;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author javie
 */
@Local
public interface AdminManagerEJBLocal {
    /**
     * 
     * @param id
     * @return
     * @throws ReadException 
     */
    public Admin findAdminById(Integer id) throws ReadException;
    
    /**
     * 
     * @return
     * @throws ReadException 
     */
    public Set<Admin> findAllAdmins() throws ReadException;
            
    /**
     * 
     * @param admin
     * @throws CreateException 
     */
    public void createAdmin(Admin admin) throws CreateException;
    
    /**
     * 
     * @param admin
     * @throws UpdateException 
     */
    public void updateAdmin(Admin admin) throws UpdateException;
    
    /**
     * 
     * @param admin
     * @throws DeleteException 
     */
    public void deleteAdmin(Admin admin) throws DeleteException;
}
