/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is the entity player
 * 
 * @author javie
 */
@Entity
@Table(name="player",schema="esport_six")
public class Player extends User{
    /**
     * Id field for the player entity
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    /**
     * Active field for the player entity
     */
    private Boolean active;
    /**
     * nickname field for the player entity
     */
    private String nickname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
    
    
}
