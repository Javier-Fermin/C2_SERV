/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author javie
 */
@Entity
@Table(name = "stats", schema = "esport_six")
@NamedQueries({
    @NamedQuery(name="findStatsByPlayerNickname",
            query="SELECT s FROM Stats s WHERE s.player.nickname = :nickname"),
    @NamedQuery(name="findStatsByMatchId",
        query="SELECT s FROM Stats s WHERE s.match.id = :matchId"),
    @NamedQuery(name="findStatsByLeagueName",
        query="SELECT s FROM Stats s WHERE s.match in (SELECT m.id FROM Match m WHERE m.league.name = :leagueName)"),
    @NamedQuery(name="findStatsByTournamentName",
        query="SELECT s FROM Stats s WHERE s.match in (SELECT m.id FROM Match m WHERE m.tournament.name = :tournamentName)")
})
public class Stats implements Serializable{

    /**
     * Id field for the Stats entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Kills, deaths and assists fields for the Stats entity
     */
    private Integer kills,

    /**
     * Kills, deaths and assists fields for the Stat entity
     */

    /**
     * Kills, deaths and assists fields for the Stats entity
     */
    deaths, 

    /**
     * Kills, deaths and assists fields for the Stat entity
     */

    /**
     * Kills, deaths and assists fields for the Stats entity
     */
    assists;

    /**
     * Team field of the Stats entity
     */
    @Enumerated(EnumType.ORDINAL)
    private Team team;

    /**
     * Player of the play entity
     */
    @ManyToOne
    private Player player;

    /**
     * Match of the play entity
     */
    @ManyToOne
    private Match match;

    public Stats() {
    }

    public Stats(Integer id, Integer kills, Integer deaths, Integer assists, Team team, Player player, Match match) {
        this.id = id;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.team = team;
        this.player = player;
        this.match = match;
    }

    /**
     * 
     * 
     * @return 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * 
     * @return 
     */
    public Integer getKills() {
        return kills;
    }

    /**
     * 
     * 
     * @param kills 
     */
    public void setKills(Integer kills) {
        this.kills = kills;
    }

    /**
     * 
     * 
     * @return 
     */
    public Integer getDeaths() {
        return deaths;
    }

    /**
     * 
     * 
     * @param deaths 
     */
    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    /**
     * 
     * 
     * @return 
     */
    public Integer getAssists() {
        return assists;
    }

    /**
     * 
     * 
     * @param assists 
     */
    public void setAssists(Integer assists) {
        this.assists = assists;
    }

    /**
     * 
     * 
     * @return 
     */
    public Team getTeam() {
        return team;
    }

    /**
     * 
     * 
     * @param team 
     */
    public void setTeam(Team team) {
        this.team = team;
    }

    /**
     * 
     * 
     * @return 
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * 
     * 
     * @param player 
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * 
     * 
     * @return 
     */
    public Match getMatch() {
        return match;
    }

    /**
     * 
     * 
     * @param match 
     */
    public void setMatch(Match match) {
        this.match = match;
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
        if (!(obj instanceof Stats)) {
            return false;
        }
        Stats other = (Stats) obj;
        if (this.id != null && this.id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 
     * @return 
     */
    @Override
    public String toString() {
        return "Stats{" + "id=" + id + ", kills=" + kills + ", deaths=" + deaths + ", assists=" + assists + ", team=" + team + ", player=" + player + ", match=" + match + '}';
    }
}
