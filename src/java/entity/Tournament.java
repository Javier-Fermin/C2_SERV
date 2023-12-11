/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.entity;

import java.entity.Sponsor;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This class is for the Tournament entity
 * 
 * @author javie
 */
@Entity
@Table(name="tournament",schema="esport_six")
public class Tournament {
    /**
     * Id field for the tournament entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /**
     * bestOf field of the tournament entity
     */
    private Integer bestOf;
    /**
     * Date field for the tournament entity
     */
    @Temporal(TemporalType.DATE)
    private Date date;
    /**
     * sponsors of the tournament entity
     */
    private Set<Sponsor> sponsors;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBestOf() {
        return bestOf;
    }

    public void setBestOf(Integer bestOf) {
        this.bestOf = bestOf;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Sponsor> getSponsors() {
        return sponsors;
    }

    public void setSponsors(Set<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }
    
    
}
