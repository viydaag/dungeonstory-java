package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import com.dungeonstory.backend.data.enums.Skill;

/**
 * Entity implementation class for Entity: MonsterSkill
 *
 */
@Entity
@Table(name = "MonsterSkill")
@IdClass(MonsterSkillId.class)
public class MonsterSkill implements Serializable {

    private static final long serialVersionUID = -1028440230820842273L;

    @Id
    @ManyToOne
    @JoinColumn(name = "monsterId")
    private Monster monster;

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "skill", nullable = false)
    private Skill skill;

    @Min(value = 0)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "bonus")
    private Integer bonus;

    public MonsterSkill() {
        super();
    }

    public Monster getMonster() {
        return this.monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Skill getSkill() {
        return this.skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Integer getBonus() {
        return this.bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }
    
    @Override
    public String toString() {
        return skill.getName() + " +" + bonus;
    }

}
