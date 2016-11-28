package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "MonsterSavingThrow")
@IdClass(MonsterSavingThrowId.class)
public class MonsterSavingThrow implements Serializable {

    private static final long serialVersionUID = 3509890813333982431L;

    @Id
    @ManyToOne
    @JoinColumn(name = "monsterId")
    private Monster monster;

    @Id
    @ManyToOne
    @JoinColumn(name = "abilityId")
    private Ability ability;

    @Min(value = 0)
    @Column(name = "bonus")
    private Integer bonus;

    public MonsterSavingThrow() {
        super();
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

}
