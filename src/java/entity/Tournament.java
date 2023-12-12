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
 * This class is for the Tournament entity
 * 
 * @author javie
 */
@Entity
@Table(name="tournament",schema="esport_six")
@NamedQueries({
    @NamedQuery(name="findTournamentsByName", query="SELECT t FROM tournament t WHERE t.name like :n"), //setParameter(n, '%'+name+'%');
    @NamedQuery(name="findTournamentsByDate", query="SELECT t FROM tournament WHERE t.date = :date"),
    @NamedQuery(name="findTournamentsByFormat", query="SELECT t FROM tournament WHERE t.bestOf = :bestOf"),
    @NamedQuery(name="findMatchTournament", query="SELECT t FROM tournament WHERE t.id = (SELECT m.tournament.id FROM match m WHERE m.id = :id)")
})


public class Tournament implements Serializable{
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
    /***
     * Matches of the tournament
     */
    @OneToMany(mappedBy = "tournament", fetch = FetchType.EAGER)
    private Set<Match> matches;
    
    
    //Tournament's Getters and Setters

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
    
     public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final Tournament other = (Tournament) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tournament{" + "id=" + id + '}';
    }
}
