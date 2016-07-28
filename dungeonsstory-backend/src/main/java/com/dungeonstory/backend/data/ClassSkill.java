package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ClassSkill")
@IdClass(ClassSkillId.class)
public class ClassSkill implements Serializable {

    private static final long serialVersionUID = -694396032772798310L;

    @Id
    @ManyToOne
    @JoinColumn(name = "classId")
    private DSClass classe;

    @Id
    @ManyToOne
    @JoinColumn(name = "skillId")
    private Skill skill;

    public ClassSkill() {

    }

    public DSClass getClasse() {
        return classe;
    }

    public void setClasse(DSClass klass) {
        this.classe = klass;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

}
