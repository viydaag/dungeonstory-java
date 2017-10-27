package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

@Entity
@IdClass(ClassLevelBonusId.class)
@Table(name = "ClassLevelBonus")
public class ClassLevelBonus implements Serializable {

    private static final long serialVersionUID = 2087402735649166519L;

    @Id
    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
    @JoinColumn(name = "classId")
    private DSClass classe;

    @Id
    @NotNull
    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
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
    private boolean deity;

    @Min(value = 0)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "kiPoints")
    private Integer kiPoints;

    @Min(value = 0)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "sorceryPoints")
    private Integer sorceryPoints;

    @Min(value = 0)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "ragePoints")
    private Integer ragePoints;

    @Min(value = 0)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "rageDamageBonus")
    private Integer rageDamageBonus;

    @Min(value = 0)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "movementBonus")
    private Integer movementBonus;

    @Pattern(regexp = "^$|(\\d+d\\d+)")
    @Column(name = "martialArtsDamage")
    private String martialArtsDamage;

    @Pattern(regexp = "^$|(\\d+d\\d+)")
    @Column(name = "sneakAttackDamage")
    private String sneakAttackDamage;

    @Min(value = 0)
    @Digits(integer = 2, fraction = 0)
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

    public boolean getHasAbilityScoreImprovement() {
        return hasAbilityScoreImprovement;
    }

    public void setHasAbilityScoreImprovement(boolean hasAbilityScoreImprovement) {
        this.hasAbilityScoreImprovement = hasAbilityScoreImprovement;
    }

    public boolean getChooseClassSpecialization() {
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

    public boolean getDeity() {
        return deity;
    }

    public void setDeity(boolean deity) {
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
