/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This class has the data of a User that has admin privileges
 * 
 * @author javie
 */
@Entity
@Table(name="admin",schema="esport_six")
public class Admin extends User{
    /**
     * Id field for the Admin entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    /**
     * This is the date when the Admin joined the application
     */
    @Temporal(TemporalType.DATE)
    private Date joinDate;

    /**
     * 
     * @return 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return 
     */
    public Date getJoinDate() {
        return joinDate;
    }

    /**
     * 
     * @param joinDate 
     */
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    
    /**
     * 
     * 
     * @return 
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * 
     * 
     * @param obj
     * @return 
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Admin)) {
            return false;
        }
        Admin other = (Admin) obj;
        if (this.id != null && this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", joinDate=" + joinDate + '}';
    }
    
    
}
