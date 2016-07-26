package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "FeatPrerequisite")
public class FeatPrerequisite extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 2932393218427906016L;

    //TODO : réviser les types
    public enum PrerequisiteType {
        FEAT, ABILITY, ATTACK_BONUS, LEVEL, CLASSE, SKILL
    }

    @ManyToOne
    @JoinColumn(name = "featId", nullable = false)
    private Feat feat;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private PrerequisiteType type;

    @JoinColumn(name = "featPrerequisiteId", nullable = true)
    private FeatPrerequisite featPrerequisite;

    @JoinColumn(name = "abilityId", nullable = true)
    private Ability ability;

    @Column(name = "abilityValue")
    private int abilityValue;

    @Column(name = "baseAttackBonus")
    private int baseAttackBonus;

    @Column(name = "level")
    private int level;

    @JoinColumn(name = "classId", nullable = true)
    private DSClass classe;

    @JoinColumn(name = "skillId", nullable = true)
    private Skill skill;

    @Column(name = "skillRank")
    private int skillRank;

    public FeatPrerequisite() {
        // TODO Auto-generated constructor stub
    }

    public Feat getFeat() {
        return feat;
    }

    public void setFeat(Feat feat) {
        this.feat = feat;
    }

    public PrerequisiteType getType() {
        return type;
    }

    public void setType(PrerequisiteType type) {
        this.type = type;
    }

    public FeatPrerequisite getFeatPrerequisite() {
        return featPrerequisite;
    }

    public void setFeatPrerequisite(FeatPrerequisite featPrerequisite) {
        this.featPrerequisite = featPrerequisite;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public int getAbilityValue() {
        return abilityValue;
    }

    public void setAbilityValue(int abilityValue) {
        this.abilityValue = abilityValue;
    }

    public int getBaseAttackBonus() {
        return baseAttackBonus;
    }

    public void setBaseAttackBonus(int baseAttackBonus) {
        this.baseAttackBonus = baseAttackBonus;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public DSClass getClasse() {
        return classe;
    }

    public void setClasse(DSClass classe) {
        this.classe = classe;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getSkillRank() {
        return skillRank;
    }

    public void setSkillRank(int skillRank) {
        this.skillRank = skillRank;
    }

}
