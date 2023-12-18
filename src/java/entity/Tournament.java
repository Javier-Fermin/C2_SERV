/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import entity.Sponsor;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is for the Tournament entity
 * 
 * @author Fran
 */
@Entity

@NamedQueries({
    @NamedQuery(name="findTournamentsByName", query="SELECT t FROM Tournament t WHERE t.name like :n"), //setParameter(n, '%'+name+'%');
    @NamedQuery(name="findTournamentsByDate", query="SELECT t FROM Tournament t WHERE t.date = :date"),
    @NamedQuery(name="findTournamentsByFormat", query="SELECT t FROM Tournament t WHERE t.bestOf = :bestOf"),
    @NamedQuery(name="findMatchTournament", query="SELECT t FROM Tournament t WHERE t.idTournament IN (SELECT m.tournament.idTournament FROM Match m WHERE m.id = :id)"),
    @NamedQuery(name="findAllTournaments", query="SELECT t FROM Tournament t")
})
@Table(name="tournament",schema="esport_six")
@XmlRootElement
public class Tournament implements Serializable{
    /**
     * Id field for the tournament entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTournament;
    
    /***
     * Name field of the tournament entity
     */
    private String name;
    
    /***
     * Description field of the tournament entity
     */
    private String description;
    
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
     * Sponsors of the tournament entity
     */
    @ManyToMany
    @JoinTable(name="tournament_sponsor", schema="esport_six", 
            joinColumns = { @JoinColumn(name = "idTournament") }, 
            inverseJoinColumns = { @JoinColumn(name = "idSponsor")})
    private Set<Sponsor> sponsors;
    
    /***
     * Matches of the tournament
     */
    @OneToMany(mappedBy = "tournament", fetch = FetchType.EAGER)
    private Set<Match> matches;
    
    
// -------------- TOURNAMENT || SETTERS & GETTERS ---------------

    public Tournament() {
    }

    public Tournament(Integer idTournament, String name, String description, Integer bestOf, Date date, Set<Sponsor> sponsors, Set<Match> matches) {
        this.idTournament = idTournament;
        this.name = name;
        this.description = description;
        this.bestOf = bestOf;
        this.date = date;
        this.sponsors = sponsors;
        this.matches = matches;
    }
   
    
    /***
     * Method that return the id of the Tournament
     * @return idTournament The id of the Tournament
     */
    public Integer getIdTournament() {
        return idTournament;
    }

    /***
     * Method that defines a value to the Tournament id attribute
     * @param idTournament the value that the id obtains
     */
    public void setIdTournament(Integer idTournament) {
        this.idTournament = idTournament;
    }

    /***
     * Method that return the name of the Tournament
     * @return name The name of the Tournament
     */
    public String getName() {
        return name;
    }
    /***
     * Method that defines a value to the Tournament name attribute
     * @param name the value that the name obtains
     */
    public void setName(String name) {
        this.name = name;
    }

    /***
     * Method that return the description of the Tournament
     * @return description The description of the Tournament
     */
    public String getDescription() {
        return description;
    }

    /***
     * Method that defines a value to the Tournament description attribute
     * @param description the value that the description obtains
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /***
     * Method that return the bestOf of the Tournament
     * @return bestOf The bestOf of the Tournament
     */
    public Integer getBestOf() {
        return bestOf;
    }

    /***
     * Method that defines a value to the Tournament bestOf attribute
     * @param bestOf the value that the bestOf obtains
     */
    public void setBestOf(Integer bestOf) {
        this.bestOf = bestOf;
    }

    /***
     * Method that return the date of the Tournament
     * @return date The date of the Tournament
     */
    public Date getDate() {
        return date;
    }

    /***
     * Method that defines a value to the Tournament date attribute
     * @param date the value that the date obtains
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /***
     * Method that returns all the Tournament sponsors
     * @return sponsors A collection of Sponsor objects
     */
    @XmlTransient
    public Set<Sponsor> getSponsors() {
        return sponsors;
    }

    /***
     * Method that defines the collection of sponsors of the Tournament
     * @param sponsors a collection of Sponsor objects
     */
    public void setSponsors(Set<Sponsor> sponsors) {
        this.sponsors = sponsors;
    }
    
    /***
     * Method that returns all the matches of the Tournament
     * @return a collection of {@link Match} objects
     */
    @XmlTransient
     public Set<Match> getMatches() {
        return matches;
    }

     /***
     * Method that defines the collection of match of the Tournament
     * @param matches a collection of {@link Match}
     */
    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

 // ------------------- METHODS --------------------   
    
    /**
     * HashCode method implementation for the entity.
     * @return An integer value as hashcode for the object. 
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.idTournament);
        return hash;
    }
    
    /**
     * This method compares two tournament entities for equality. This implementation
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
        final Tournament other = (Tournament) obj;
        if (!Objects.equals(this.idTournament, other.idTournament)) {
            return false;
        }
        return true;
    }
    
    /**
     * This method returns a String representation for a tournament entity instance.
     * @return The String representation for the tournament object. 
     */
    @Override
    public String toString() {
        return "Tournament{" + "idTournament=" + idTournament + ", name=" + name + ", description=" + description + ", bestOf=" + bestOf + ", date=" + date + ", sponsors=" + sponsors + ", matches=" + matches + '}';
    }
    
}
