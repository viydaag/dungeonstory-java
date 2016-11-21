package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@IdClass(ClassLevelBonusId.class)
@Table(name = "ClassLevelBonus")
public class ClassLevelBonus implements Serializable {

    private static final long serialVersionUID = 2087402735649166519L;

    @Id
    @ManyToOne
    @JoinColumn(name = "classId")
    private DSClass classe;

    @Id
    @ManyToOne
    @JoinColumn(name = "levelId")
    private Level level;

    @Column(name = "abilityScoreImprovement")
    private boolean hasAbilityScoreImprovement;
    
    @Column(name = "chooseClassSpecialization")
    private boolean chooseClassSpecialization;

    @Column(name = "favoredEnemy")
    private Boolean favoredEnemy;

    @Column(name = "naturalExplorer")
    private Boolean naturalExplorer;

    @Column(name = "deity")
    private Boolean deity;

    @Column(name = "kiPoints")
    private Integer kiPoints;

    @Column(name = "sorceryPoints")
    private Integer sorceryPoints;

    @Column(name = "ragePoints")
    private Integer ragePoints;

    @Column(name = "rageDamageBonus")
    private Integer rageDamageBonus;

    @Column(name = "movementBonus")
    private Integer movementBonus;

    @Pattern(regexp = "\\d+d\\d+")
    @Column(name = "martialArtsDamage")
    private String martialArtsDamage;

    @Pattern(regexp = "\\d+d\\d+")
    @Column(name = "sneakAttackDamage")
    private String sneakAttackDamage;

    @Column(name = "invocationsKnown")
    private Integer invocationsKnown;


    public ClassLevelBonus() {

    }

    public DSClass getClasse() {
        return classe;
    }

    public void setClasse(DSClass classe) {
        this.classe = classe;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public boolean isHasAbilityScoreImprovement() {
        return hasAbilityScoreImprovement;
    }

    public void setHasAbilityScoreImprovement(boolean hasAbilityScoreImprovement) {
        this.hasAbilityScoreImprovement = hasAbilityScoreImprovement;
    }

    public boolean isChooseClassSpecialization() {
        return chooseClassSpecialization;
    }

    public void setChooseClassSpecialization(boolean chooseClassSpecialization) {
        this.chooseClassSpecialization = chooseClassSpecialization;
    }

    public Boolean getFavoredEnemy() {
        return favoredEnemy;
    }

    public void setFavoredEnemy(Boolean favoredEnemy) {
        this.favoredEnemy = favoredEnemy;
    }

    public Boolean getNaturalExplorer() {
        return naturalExplorer;
    }

    public void setNaturalExplorer(Boolean naturalExplorer) {
        this.naturalExplorer = naturalExplorer;
    }

    public Boolean getDeity() {
        return deity;
    }

    public void setDeity(Boolean deity) {
        this.deity = deity;
    }

    public Integer getKiPoints() {
        return kiPoints;
    }

    public void setKiPoints(Integer kiPoints) {
        this.kiPoints = kiPoints;
    }

    public Integer getSorceryPoints() {
        return sorceryPoints;
    }

    public void setSorceryPoints(Integer sorceryPoints) {
        this.sorceryPoints = sorceryPoints;
    }

    public Integer getRagePoints() {
        return ragePoints;
    }

    public void setRagePoints(Integer ragePoints) {
        this.ragePoints = ragePoints;
    }

    public Integer getRageDamageBonus() {
        return rageDamageBonus;
    }

    public void setRageDamageBonus(Integer rageDamageBonus) {
        this.rageDamageBonus = rageDamageBonus;
    }

    public Integer getMovementBonus() {
        return movementBonus;
    }

    public void setMovementBonus(Integer movementBonus) {
        this.movementBonus = movementBonus;
    }

    public String getMartialArtsDamage() {
        return martialArtsDamage;
    }

    public void setMartialArtsDamage(String martialArtsDamage) {
        this.martialArtsDamage = martialArtsDamage;
    }

    public String getSneakAttackDamage() {
        return sneakAttackDamage;
    }

    public void setSneakAttackDamage(String sneakAttackDamage) {
        this.sneakAttackDamage = sneakAttackDamage;
    }

    public Integer getInvocationsKnown() {
        return invocationsKnown;
    }

    public void setInvocationsKnown(Integer invocationsKnown) {
        this.invocationsKnown = invocationsKnown;
    }

}
