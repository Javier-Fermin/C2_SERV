/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author javie
 */
@Embeddable
public class StatsId implements Serializable{
    private Integer playerId;
    private Integer matchId;

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public StatsId() {
    }

    public StatsId(Integer playerId, Integer matchId) {
        this.playerId = playerId;
        this.matchId = matchId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.playerId);
        hash = 53 * hash + Objects.hashCode(this.matchId);
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
        final StatsId other = (StatsId) obj;
        if (!Objects.equals(this.playerId, other.playerId)) {
            return false;
        }
        if (!Objects.equals(this.matchId, other.matchId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "StatsId{" + "playerId=" + playerId + ", matchId=" + matchId + '}';
    }
    
    
}
