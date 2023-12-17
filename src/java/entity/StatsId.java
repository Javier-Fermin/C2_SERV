/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author javie
 */
@Embeddable
public class StatsId implements Serializable{
    private Integer playerId;
    private Integer matchId;
}
