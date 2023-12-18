/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is for the Sponsor entity
 * 
 * @author Fran
 */
@Entity
@Table(name="sponsor",schema="esport_six")
@NamedQuery(name="findAllSponsors", query="SELECT s FROM Sponsor s")

public class Sponsor implements Serializable{
    /**
     * Id field for the sponsor entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idSponsor;
    /**
     * name field of the sponsor entity
     */
    private String name;
    /**
     * cash field for the sponsor entity
     */
    private Float cash;
    /**
     * createdDate field for the sponsor entity
     */
    @Temporal(TemporalType.DATE)
    private Date createdDate;
    
    /***
     * Tournaments this sponsor supports
     */
    @ManyToMany(mappedBy="sponsors")
    private Set<Tournament> tournaments;

    
// -------------- SPONSOR SETTERS & GETTERS ----------------
    
    /***
     * Method that return the id of the Sponsor
     * @return id The id of the Sponsor
     */
    public Integer getIdSponsor() {
        return idSponsor;
    }

    /***
     * Method that defines a value to the Sponsor id attribute
     * @param idSponsor the value that the id obtains
     */
    public void setIdSponsor(Integer idSponsor) {
        this.idSponsor = idSponsor;
    }

    /***
     * Method that return the name of the Sponsor
     * @return name The name of the Sponsor
     */
    public String getName() {
        return name;
    }

    /***
     * Method that defines a value to the Sponsor name attribute
     * @param name the value that the name obtains
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Method that return the cash of the Sponsor
     * @return cash The id of the Sponsor
     */
    public Float getCash() {
        return cash;
    }

    /***
     * Method that defines a value to the Sponsor cash attribute
     * @param cash the value that the cash obtains
     */
    public void setCash(Float cash) {
        this.cash = cash;
    }

    /***
     * Method that return the createdDate of the Sponsor
     * @return createdDate The createdDate of the Sponsor
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /***
     * Method that defines a value to the Sponsor createdDate attribute
     * @param createdDate the value that the createdDate obtains
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /***
     * Method that returns all the {@link Tournament} that the sponsor supports
     * @return sponsors A collection of {@link Tournament} objects
     */
    @XmlTransient
    public Set<Tournament> getTournaments() {
        return tournaments;
    }
    
    /***
     * Method that defines the collection of supported Tournament
     * @param tournaments a collection of {@link Tournament} objects
     */
    public void setTournaments(Set<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

// ------------------- METHODS --------------------
    
    /**
     * HashCode method implementation for the entity.
     * @return An integer value as hashcode for the object. 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.idSponsor);
        return hash;
    }

    /**
     * This method compares two sponsor entities for equality. This implementation
     * compare id field value for equality.
     * @param obj The object to compare to.
     * @return True if objects are equals, otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sponsor other = (Sponsor) obj;
        if (!Objects.equals(this.idSponsor, other.idSponsor)) {
            return false;
        }
        return true;
    }

    /**
     * This method returns a String representation for a sponsor entity instance.
     * @return The String representation for the sponsor object. 
     */
    @Override
    public String toString() {
        return "Sponsor{" + "idSponsor=" + idSponsor + ", name=" + name + ", cash=" + cash + ", createdDate=" + createdDate + ", tournaments=" + tournaments + '}';
    }
    
    
}
