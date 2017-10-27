package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import com.dungeonstory.backend.data.WeaponType.UsageType;

@Entity
@Table(name = "MonsterAction")
public class MonsterAction extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 7097778457561595984L;

    @NotNull
    @Size(min = 1)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "usageType", nullable = false)
    @Enumerated(EnumType.STRING)
    private UsageType usageType;

    @NotNull
    @Pattern(regexp = "\\d+d\\d+([\\+\\-]\\d+)*")
    @Column(name = "damage", nullable = false)
    private String damage;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "damageTypeId", nullable = false)
    private DamageType damageType;

    @Min(value = 0)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "bonusToHit")
    private Integer bonusToHit = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "conditionName")
    private Condition condition;

    @ManyToOne
    @JoinFetch(JoinFetchType.OUTER)
    @JoinColumn(name = "savingThrowAbilityId")
    private Ability savingThrowToCondition;

    @NotNull
    @Pattern(regexp = "\\d+d\\d+([\\+\\-]\\d+)*")
    @Column(name = "extraDamage", nullable = false)
    private String extraDamage;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "extraDamageTypeId", nullable = false)
    private DamageType extraDamageType;

    @Column(name = "multiAttack")
    private boolean multiAttack;
    
    @Min(value = 1)
    @Digits(integer = 1, fraction = 0)
    @Column(name = "multiAttackRank")
    private Integer multiAttackRank;

    @Min(value = 1)
    @Digits(integer = 1, fraction = 0)
    @Column(name = "nbPerRound", nullable = false)
    private Integer nbPerRound = 1;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "monsterId", nullable = false)
    private Monster monster;

    public MonsterAction() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDamage() {
        return damage;
    }

    public void setDamage(String damage) {
        this.damage = damage;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public UsageType getUsageType() {
        return usageType;
    }

    public void setUsageType(UsageType usageType) {
        this.usageType = usageType;
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public Integer getBonusToHit() {
        return bonusToHit;
    }

    public void setBonusToHit(Integer bonusToHit) {
        this.bonusToHit = bonusToHit;
    }

    public Ability getSavingThrowToCondition() {
        return savingThrowToCondition;
    }

    public void setSavingThrowToCondition(Ability savingThrowToCondition) {
        this.savingThrowToCondition = savingThrowToCondition;
    }

    public String getExtraDamage() {
        return extraDamage;
    }

    public void setExtraDamage(String extraDamage) {
        this.extraDamage = extraDamage;
    }

    public DamageType getExtraDamageType() {
        return extraDamageType;
    }

    public void setExtraDamageType(DamageType extraDamageType) {
        this.extraDamageType = extraDamageType;
    }

    public boolean getMultiAttack() {
        return multiAttack;
    }

    public void setMultiAttack(boolean multiAttack) {
        this.multiAttack = multiAttack;
    }

    public Integer getMultiAttackRank() {
        return multiAttackRank;
    }

    public void setMultiAttackRank(Integer multiAttackRank) {
        this.multiAttackRank = multiAttackRank;
    }

    public Integer getNbPerRound() {
        return nbPerRound;
    }

    public void setNbPerRound(Integer nbPerRound) {
        this.nbPerRound = nbPerRound;
    }

}
