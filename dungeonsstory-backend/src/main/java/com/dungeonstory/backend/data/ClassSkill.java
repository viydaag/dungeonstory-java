package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
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
    @Column(name = "classId")
    private Long classId;

    @Id
    @Column(name = "skillId")
    private Long skillId;

    @ManyToOne
    @JoinColumn(name = "classId", updatable = false, insertable = false)
    private DSClass classe;

    @ManyToOne
    @JoinColumn(name = "skillId", updatable = false, insertable = false)
    private Skill skill;

    public ClassSkill() {

    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
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
