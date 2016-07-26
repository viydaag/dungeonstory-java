package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "ArmorType")
public class ArmorType extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 6701206243601789036L;

    public static final int NO_MAX_DEX_BONUS = -1;

    public static final int MINIMUM_STRENGTH = 1;

    public enum ProficiencyType {
        LIGHT, MEDIUM, HEAVY, SHIELD
    }

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Column(name = "proficiencyType", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProficiencyType proficiencyType;

    @NotNull
    @Min(value = NO_MAX_DEX_BONUS)
    @Column(name = "maxDexBonus", nullable = false)
    private int maxDexBonus = NO_MAX_DEX_BONUS;

    @NotNull
    @Min(value = 0)
    @Column(name = "baseArmorClass", nullable = false)
    private int baseArmorClass;

    @Column(name = "stealthDisavantage", nullable = false)
    private boolean stealthDisavantage;

    // The minimum strength to be able to wear the armor.
    @NotNull
    @Min(value = MINIMUM_STRENGTH)
    @Column(name = "minStrength", nullable = false)
    private int minStrength = MINIMUM_STRENGTH;

    @NotNull
    @Min(value = 0)
    @Column(name = "baseWeight", nullable = false)
    private int baseWeight;

    @Min(value = 0)
    @Column(name = "speed", nullable = false)
    private int speed;

    public ArmorType() {
        super();
    }

    public ArmorType(String name, String description, ProficiencyType proficiencyType, int maxDexBonus,
            int baseArmorClass, boolean stealthDisavantage, int minStrength, int baseWeight, int speed) {
        this();
        this.name = name;
        this.description = description;
        this.proficiencyType = proficiencyType;
        this.maxDexBonus = maxDexBonus;
        this.baseArmorClass = baseArmorClass;
        this.stealthDisavantage = stealthDisavantage;
        this.minStrength = minStrength;
        this.baseWeight = baseWeight;
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProficiencyType getProficiencyType() {
        return proficiencyType;
    }

    public void setProficiencyType(ProficiencyType proficiencyType) {
        this.proficiencyType = proficiencyType;
    }

    public int getMaxDexBonus() {
        return maxDexBonus;
    }

    public void setMaxDexBonus(int maxDexBonus) {
        this.maxDexBonus = maxDexBonus;
    }

    public int getBaseArmorClass() {
        return baseArmorClass;
    }

    public void setBaseArmorClass(int baseArmorClass) {
        this.baseArmorClass = baseArmorClass;
    }

    public int getBaseWeight() {
        return baseWeight;
    }

    public void setBaseWeight(int baseWeight) {
        this.baseWeight = baseWeight;
    }

    public boolean getStealthDisavantage() {
        return stealthDisavantage;
    }

    public void setStealthDisavantage(boolean stealthDisavantage) {
        this.stealthDisavantage = stealthDisavantage;
    }

    public int getMinStrength() {
        return minStrength;
    }

    public void setMinStrength(int minStrength) {
        this.minStrength = minStrength;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return getName();
    }
}
