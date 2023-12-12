/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
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
import javax.persistence.TemporalType;

/**
 * This is the class for League data
 *
 * @author javie
 */
/**
 * NamedQueries for League entity
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "findAllLeagues", query = "Select l from League l"),
    
    @NamedQuery(name = "findLeagueByName", query = "Select l from League l WHERE p.name=:name"),
    
    @NamedQuery(name = "findAllFinishLeagues", query = "Select l from League l WHERE l.endDate < :today"), 
    
    @NamedQuery(name = "findAllUnstartedLeagues", query = "Select l from League l WHERE l.startDate > :today"),
    
    @NamedQuery(name = "findLeagueForMatch", query = "Select l from League l WHERE l.id = (Select m.league.id from Match m where m.id = :id)")
})

@Table(name = "league", schema = "esport_six")
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
    @Temporal(TemporalType.DATE)
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
    private Set<Match> matches;

    public League() {
    }

    public League(Integer id, Date startDate, Date endDate, String name, String description, Set<Match> matches) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.name = name;
        this.description = description;
        this.matches = matches;
    }

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

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

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

    @Override
    public String toString() {
        return "League{" + "id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", name=" + name + ", description=" + description + ", matches=" + matches + '}';
    }

}
