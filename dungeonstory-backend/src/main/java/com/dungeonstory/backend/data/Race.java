package com.dungeonstory.backend.data;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.dungeonstory.backend.data.enums.Condition;
import com.dungeonstory.backend.data.enums.Language;
import com.dungeonstory.backend.data.enums.Skill;
import com.dungeonstory.backend.data.enums.DamageType;

import org.eclipse.persistence.annotations.PrivateOwned;

@Entity
@Table(name = "Race")
public class Race extends AbstractTimestampEntity {

    private static final long serialVersionUID = -8654082699083199159L;
    
    public enum Size {
        SMALL,
        MEDIUM
    }

    @NotNull
    @javax.validation.constraints.Size(min = 1)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "traits", columnDefinition = "TEXT")
    private String traits;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 1, fraction = 0)
    @Column(name = "strModifier")
    private int strModifier = 0;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 1, fraction = 0)
    @Column(name = "dexModifier")
    private int dexModifier = 0;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 1, fraction = 0)
    @Column(name = "conModifier")
    private int conModifier = 0;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 1, fraction = 0)
    @Column(name = "intModifier")
    private int intModifier = 0;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 1, fraction = 0)
    @Column(name = "wisModifier")
    private int wisModifier = 0;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 1, fraction = 0)
    @Column(name = "chaModifier")
    private int chaModifier = 0;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 3, fraction = 0)
    @Column(name = "minAge")
    private int minAge = 0;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 4, fraction = 0)
    @Column(name = "maxAge")
    private int maxAge = 0;
    
    @NotNull
    @Column(name = "size", nullable = false)
    @Enumerated(EnumType.STRING)
    private Size size;
    
    @NotNull
    @Pattern(regexp = "\\d+\\'(\\d+\\\")*")
    @Column(name = "averageHeight")
    private String averageHeight;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 3, fraction = 0)
    @Column(name = "averageWeight")
    private int averageWeight;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 2, fraction = 0)
    @Column(name = "speed")
    private int speed = 0;
    
    @ElementCollection(targetClass = Language.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "RaceLanguage", joinColumns = @JoinColumn(name = "raceId", nullable = false))
    @Column(name = "language", nullable = false)
    private Set<Language> languages;
    
    @Column(name = "extraLanguage")
    private boolean extraLanguage;
    
    @ElementCollection(targetClass = Condition.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "RaceSavingThrowProficiencies", joinColumns = @JoinColumn(name = "raceId", nullable = false))
    @Column(name = "conditionName", nullable = false)
    @PrivateOwned
    private Set<Condition> savingThrowProficiencies;
    
    @ElementCollection(targetClass = ArmorType.ProficiencyType.class)
    @CollectionTable(name = "RaceArmorProficiencies", joinColumns = @JoinColumn(name = "raceId", nullable = false))
    @Column(name = "proficiency", nullable = false)
    @Enumerated(EnumType.STRING)
    @PrivateOwned
    private Set<ArmorType.ProficiencyType> armorProficiencies;
    
    @ManyToMany
    @JoinTable(name = "RaceWeaponProficiencies", joinColumns = {
        @JoinColumn(name = "raceId", referencedColumnName = "id") }, 
            inverseJoinColumns = { @JoinColumn(name = "weaponTypeId", referencedColumnName = "id") })
    private Set<WeaponType> weaponProficiencies;

    @ElementCollection(targetClass = Skill.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "RaceSkillProficiencies", joinColumns = @JoinColumn(name = "raceId", nullable = false))
    @Column(name = "skill", nullable = false)
    private Set<Skill> skillProficiencies;
    
    @ElementCollection(targetClass = DamageType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "RaceDamageResistance", joinColumns = @JoinColumn(name = "raceId", nullable = false))
    @Column(name = "damageResistance", nullable = false)
    private Set<DamageType> damageResistance;
    
    @Column(name = "image")
    private byte[] image;

    public Race() {
        super();
        languages = new HashSet<Language>();
        savingThrowProficiencies = new HashSet<Condition>();
        armorProficiencies = new HashSet<ArmorType.ProficiencyType>();
        weaponProficiencies = new HashSet<WeaponType>();
        skillProficiencies = new HashSet<Skill>();
        damageResistance = EnumSet.noneOf(DamageType.class);
    }

    public Race(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTraits() {
        return traits;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStrModifier() {
        return strModifier;
    }

    public void setStrModifier(int strModifier) {
        this.strModifier = strModifier;
    }

    public int getDexModifier() {
        return dexModifier;
    }

    public void setDexModifier(int dexModifier) {
        this.dexModifier = dexModifier;
    }

    public int getConModifier() {
        return conModifier;
    }

    public void setConModifier(int conModifier) {
        this.conModifier = conModifier;
    }

    public int getIntModifier() {
        return intModifier;
    }

    public void setIntModifier(int intModifier) {
        this.intModifier = intModifier;
    }

    public int getWisModifier() {
        return wisModifier;
    }

    public void setWisModifier(int wisModifier) {
        this.wisModifier = wisModifier;
    }

    public int getChaModifier() {
        return chaModifier;
    }

    public void setChaModifier(int chaModifier) {
        this.chaModifier = chaModifier;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public String getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(String averageHeight) {
        this.averageHeight = averageHeight;
    }

    public int getAverageWeight() {
        return averageWeight;
    }

    public void setAverageWeight(int averageWeight) {
        this.averageWeight = averageWeight;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public boolean getExtraLanguage() {
        return extraLanguage;
    }

    public void setExtraLanguage(boolean extraLanguage) {
        this.extraLanguage = extraLanguage;
    }

    public Set<Condition> getSavingThrowProficiencies() {
        return savingThrowProficiencies;
    }

    public void setSavingThrowProficiencies(Set<Condition> savingThrowProficiencies) {
        this.savingThrowProficiencies = savingThrowProficiencies;
    }

    public Set<ArmorType.ProficiencyType> getArmorProficiencies() {
        return armorProficiencies;
    }

    public void setArmorProficiencies(Set<ArmorType.ProficiencyType> armorProficiencies) {
        this.armorProficiencies = armorProficiencies;
    }

    public Set<WeaponType> getWeaponProficiencies() {
        return weaponProficiencies;
    }

    public void setWeaponProficiencies(Set<WeaponType> weaponProficiencies) {
        this.weaponProficiencies = weaponProficiencies;
    }

    public Set<Skill> getSkillProficiencies() {
        return skillProficiencies;
    }

    public void setSkillProficiencies(Set<Skill> skillProficiencies) {
        this.skillProficiencies = skillProficiencies;
    }

    public Set<DamageType> getDamageResistance() {
        return damageResistance;
    }

    public void setDamageResistance(Set<DamageType> damageResistance) {
        this.damageResistance = damageResistance;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return getName();
    }

}
