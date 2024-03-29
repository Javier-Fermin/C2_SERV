/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * This class is the entity player
 *
 * @author javie
 */
@Entity
@Table(name = "player", schema = "esport_six")
@NamedQueries({
        @NamedQuery(name = "findPlayers", query = "SELECT p FROM Player p"),
        @NamedQuery(name = "findPlayerById", query = "SELECT p FROM Player p WHERE p.id = :id"),
        @NamedQuery(name = "findPlayerByEmail", query = "SELECT p FROM Player p WHERE p.email = :email"),
        @NamedQuery(name="findPlayerByNickname",
            query="SELECT p FROM Player p WHERE p.nickname = :nickname")
})
@XmlRootElement
public class Player extends User {

    /**
     * Active field for the player entity
     */
    private Boolean active;
    /**
     * nickname field for the player entity
     */
    @Column(unique=true)
    private String nickname;
    /**
     * list of Stats
     */
    @OneToMany(mappedBy="player", fetch = FetchType.EAGER)
    private List<Stats> stats;

    public Player() {
        super();
    }
    
    public Player(Boolean active, String nickname, String name, String passwd, String phone, String email, String address, UserType userType) {
        super(name, passwd, phone, email, address, userType);
        this.active = active;
        this.nickname = nickname;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @XmlTransient
    public List<Stats> getStats() {
        return stats;
    }

    public void setStats(List<Stats> stats) {
        this.stats = stats;
    }

    /**
     * HashCode and equals for Player entity 
     */
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.active);
        hash = 59 * hash + Objects.hashCode(this.nickname);
        hash = 59 * hash + Objects.hashCode(this.stats);
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
        final Player other = (Player) obj;
        if (!Objects.equals(this.nickname, other.nickname)) {
            return false;
        }
        if (!Objects.equals(this.active, other.active)) {
            return false;
        }
        if (!Objects.equals(this.stats, other.stats)) {
            return false;
        }
        return true;
    }

    /**
     * toString to player entity
     */
    @Override
    public String toString() {
        return "Player{" + ", active=" + active + ", nickname=" + nickname + ", stats=" + stats + '}';
    }

}
