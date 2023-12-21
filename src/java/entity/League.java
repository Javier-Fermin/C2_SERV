/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This is the class for League data
 *
 * @author Emil
 */
/**
 * NamedQueries for League entity
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findAllLeagues", query = "Select l from League l"),
    
    @NamedQuery(name = "findLeagueByName", query = "Select l from League l WHERE l.name =:name"),
    
    @NamedQuery(name = "findAllFinishLeagues", query = "Select l from League l WHERE l.endDate <= :date"), 
    
    @NamedQuery(name = "findAllUnstartedLeagues", query = "Select l from League l WHERE l.startDate >= :date"),
    
    @NamedQuery(name = "findLeagueForMatch", query = "SELECT l FROM League l WHERE l.id IN (SELECT m.league.id FROM Match m WHERE m.id = :id)")
})

@Table(name = "league", schema = "esport_six")
@XmlRootElement
public class League implements Serializable {

    /**
     * Id field for the league entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * startDate and endDate fields for the league entity
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate, endDate;

    /**
     * name of the league entity
     */
    private String name;

    /**
     * description of the league entity
     */
    private String description;

    /**
     * Set of matches for league entity
     */
    @OneToMany(mappedBy = "league", fetch = FetchType.EAGER)
    private List<Match> matches;

    /**
     * constructor
     */
    public League() {
    }

    public League(Integer id, Date startDate, Date endDate, String name, String description, List<Match> matches) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.description = description;
        this.matches = matches;
    }

    /**
     * Getters and Setters for all atributes
     *  
     */
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @XmlTransient
    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
    
    /**
     * hashCode and equals for the league entity
     */

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.startDate);
        hash = 37 * hash + Objects.hashCode(this.endDate);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.matches);
        return hash;
    }

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
        final League other = (League) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.startDate, other.startDate)) {
            return false;
        }
        if (!Objects.equals(this.endDate, other.endDate)) {
            return false;
        }
        if (!Objects.equals(this.matches, other.matches)) {
            return false;
        }
        return true;
    }

    /**
     * toString for the League entity 
     */
    @Override
    public String toString() {
        return "League{" + "id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", name=" + name + ", description=" + description + ", matches=" + matches + '}';
    }

}
