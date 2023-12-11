/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This is the class for the Match data
 * 
 * @author javie
 */
@Entity
@Table(name="match",schema="esport_six")
public class Match {
    /**
     * Id field for the Match entity
     */
    @Id
    private Integer id;
    
    /**
     * playedDate field for the Match entity
     */
    private Date playedDate;
    
    /**
     * winner field for the Match entity
     */
    @Enumerated(EnumType.ORDINAL)
    private Team winner;
    
    /**
     * tournament field for the Match entity
     */
    private Tournament tournament;
    
    /**
     * league field for the Match entity
     */
    private League league;
    
    /**
     * plays of the Match entity
     */
    private Set<Stats> plays;

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

    public Set<Stats> getPlays() {
        return plays;
    }

    public void setPlays(Set<Stats> plays) {
        this.plays = plays;
    }
    
    
}
