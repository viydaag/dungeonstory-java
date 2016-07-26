package com.dungeonstory.backend.data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@DiscriminatorValue("ARMOR")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
@Table(name = "Armor")
public class Armor extends Equipment {

    private static final long serialVersionUID = 4293636155389332201L;

    @Column(name = "acBonus")
    private int acBonus;

    @Column(name = "armorCheckPenalty")
    private int armorCheckPenalty = 0;

    @Column(name = "arcaneSpellFailure")
    @Min(value = 0)
    @Max(value = 100)
    private int arcaneSpellFailure = 0;

    @Column(name = "magicalAcBonus")
    private int magicalAcBonus = 0;

    @ManyToOne
    @JoinColumn(name = "armorTypeId")
    private ArmorType armorType;

    public Armor() {
        super();
    }

    public Armor(int acBonus, int armorCheckPenalty, int arcaneSpellFailure, int magicalAcBonus, ArmorType armorType) {
        this();
        this.acBonus = acBonus;
        this.armorCheckPenalty = armorCheckPenalty;
        this.arcaneSpellFailure = arcaneSpellFailure;
        this.magicalAcBonus = magicalAcBonus;
        this.armorType = armorType;
    }

    public int getAcBonus() {
        return acBonus;
    }

    public void setAcBonus(int acBonus) {
        this.acBonus = acBonus;
    }

    public int getArmorCheckPenalty() {
        return armorCheckPenalty;
    }

    public void setArmorCheckPenalty(int armorCheckPenalty) {
        this.armorCheckPenalty = armorCheckPenalty;
    }

    public int getArcaneSpellFailure() {
        return arcaneSpellFailure;
    }

    public void setArcaneSpellFailure(int arcaneSpellFailure) {
        this.arcaneSpellFailure = arcaneSpellFailure;
    }

    public int getMagicalAcBonus() {
        return magicalAcBonus;
    }

    public void setMagicalAcBonus(int magicalAcBonus) {
        this.magicalAcBonus = magicalAcBonus;
    }

    public ArmorType getArmorType() {
        return armorType;
    }

    public void setArmorType(ArmorType armorType) {
        this.armorType = armorType;
    }

    @Override
    public String toString() {
        return getName();
    }

}
