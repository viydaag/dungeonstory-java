package com.dungeonstory.backend.data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("ARMOR")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
//@AttributeOverrides({
//    @AttributeOverride(name="created", column=@Column(name="created")),
//    @AttributeOverride(name="updated", column=@Column(name="updated")),
//    @AttributeOverride(name="version", column=@Column(name="version"))
//})
@Table(name = "Armor")
public class Armor extends Equipment {

    private static final long serialVersionUID = 4293636155389332201L;

    @Column(name = "acBonus")
    private int acBonus;

    @Column(name = "magicalAcBonus")
    private int magicalAcBonus = 0;

    @ManyToOne
    @JoinColumn(name = "armorTypeId")
    private ArmorType armorType;

    public Armor() {
        super();
    }

    public Armor(int acBonus, int magicalAcBonus, ArmorType armorType) {
        this();
        this.acBonus = acBonus;
        this.magicalAcBonus = magicalAcBonus;
        this.armorType = armorType;
    }

    public int getAcBonus() {
        return acBonus;
    }

    public void setAcBonus(int acBonus) {
        this.acBonus = acBonus;
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
