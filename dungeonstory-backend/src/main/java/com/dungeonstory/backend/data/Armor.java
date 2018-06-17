package com.dungeonstory.backend.data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.dungeonstory.backend.data.enums.ArmorType;

@Entity
@DiscriminatorValue("ARMOR")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
@Table(name = "Armor")
public class Armor extends Equipment {

    private static final long serialVersionUID = 4293636155389332201L;

    @Column(name = "armorClass")
    private int armorClass;

    @Digits(integer = 3, fraction = 0)
    @Column(name = "magicalAcBonus")
    private Integer magicalAcBonus;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "armorType", nullable = false)
    private ArmorType armorType;

    public Armor() {
        super();
    }

    public Armor(int acBonus, int magicalAcBonus, ArmorType armorType) {
        this();
        this.armorClass = acBonus;
        this.magicalAcBonus = magicalAcBonus;
        this.armorType = armorType;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public Integer getMagicalAcBonus() {
        return magicalAcBonus;
    }

    public void setMagicalAcBonus(Integer magicalAcBonus) {
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
