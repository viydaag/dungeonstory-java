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
    @ManyToOne
    @JoinColumn(name = "skillId")
    private Skill skill;

    @Min(value = 0)
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

}
