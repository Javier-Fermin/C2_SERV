/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * This is the class for the Match data
 *
 * @author imanol
 */
@Entity
@Table(name = "match", schema = "esport_six")

@NamedQueries({
    @NamedQuery(name = "findAllTournamentMatches", query = "SELECT m FROM Match m WHERE m.tournament IS NOT NULL"),
        @NamedQuery(name = "findAllLeagueMatches", query = "SELECT m FROM Match m WHERE m.league IS NOT NULL"),
        @NamedQuery(name = "findAMatch", query = "SELECT m FROM Match m WHERE m.id = :id"),
        @NamedQuery(name = "findTournamentById", query = "SELECT m FROM Match m WHERE m.tournament.id = :id"),
        @NamedQuery(name = "findLeagueById", query = "SELECT m FROM Match m WHERE m.league.id = :id"),
        @NamedQuery(name = "findMatchesByUserNickname", query = "SELECT m from Match m where m.id in (SELECT s.match.id from Stats s WHERE s.player.nickname = :nickname)")
})

public class Match implements Serializable {

    /**
     * Id field for the Match entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * playedDate field for the Match entity
     */
    @Temporal(TemporalType.DATE)
    private Date playedDate;

    /**
     * winner field for the Match entity
     */
    @Enumerated(EnumType.ORDINAL)
    private Team winner;

    /**
     * tournament field for the Match entity
     */
    @ManyToOne
    private Tournament tournament;

    /**
     * league field for the Match entity
     */
    @ManyToOne
    private League league;

    /**
     * plays of the Match entity
     */
    @OneToMany(mappedBy = "match", fetch = FetchType.EAGER)
    private Set<Stats> stats;

    /**
     * descrition of the match
     */
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPlayedDate() {
        return playedDate;
    }

    public void setPlayedDate(Date playedDate) {
        this.playedDate = playedDate;
    }

    public Team getWinner() {
        return winner;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Set<Stats> getStats() {
        return stats;
    }

    public void setPlays(Set<Stats> stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        return "Match [id=" + id + ", playedDate=" + playedDate + ", winner=" + winner + ", tournament=" + tournament
                + ", league=" + league + ", stats=" + stats + "]";
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Match)) {
            return false;
        }
        Match other = (Match) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
