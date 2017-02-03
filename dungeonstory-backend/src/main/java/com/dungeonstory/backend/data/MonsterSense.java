package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "MonsterSense")
public class MonsterSense extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = -3163306637668112844L;

    public enum Sense {
        BLINDSIGHT, DARKVISION, TREMORSENSE, TRUESIGHT
    }

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sense", nullable = false)
    private Sense sense;

    @Column(name = "distanceInFeet")
    private Integer distanceInFeet;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "monsterId", nullable = false)
    private Monster monster;

    public MonsterSense() {
        super();
    }

    public Sense getSense() {
        return sense;
    }

    public void setSense(Sense sense) {
        this.sense = sense;
    }

    public Integer getDistanceInFeet() {
        return distanceInFeet;
    }

    public void setDistanceInFeet(Integer distanceInFeet) {
        this.distanceInFeet = distanceInFeet;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

}