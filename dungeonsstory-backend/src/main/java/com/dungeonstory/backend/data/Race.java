package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Race")
public class Race extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = -8654082699083199159L;
    
    public enum Size {
        SMALL,
        MEDIUM
    }

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "shortDescription")
    private String shortDescription;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Min(value = 0)
    @Column(name = "strModifier")
    private int strModifier = 0;

    @NotNull
    @Min(value = 0)
    @Column(name = "dexModifier")
    private int dexModifier = 0;

    @NotNull
    @Min(value = 0)
    @Column(name = "conModifier")
    private int conModifier = 0;

    @NotNull
    @Min(value = 0)
    @Column(name = "intModifier")
    private int intModifier = 0;

    @NotNull
    @Min(value = 0)
    @Column(name = "wisModifier")
    private int wisModifier = 0;

    @NotNull
    @Min(value = 0)
    @Column(name = "chaModifier")
    private int chaModifier = 0;

    @NotNull
    @Min(value = 0)
    @Column(name = "minAge")
    private int minAge = 0;

    @NotNull
    @Min(value = 0)
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
    @Column(name = "averageWeight")
    private int averageWeight;

    @NotNull
    @Pattern(regexp = "\\d+d\\d+")
    @Column(name = "ageModifier")
    private String ageModifier;

    @NotNull
    @Pattern(regexp = "\\d+d\\d+")
    @Column(name = "weightModifier")
    private String weightModifier;

    @NotNull
    @Pattern(regexp = "\\d+d\\d+")
    @Column(name = "heightModifier")
    private String heightModifier;
    
    @NotNull
    @Min(value = 0)
    @Column(name = "speed")
    private int speed = 0;
    
    @ManyToMany
    @JoinTable(
        name="RaceLanguage",
        joinColumns={@JoinColumn(name="raceId", referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="languageId", referencedColumnName="id")})
    private Set<Language> languages;

    public Race() {
        super();
        languages = new HashSet<Language>();
    }

    public Race(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
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

    public String getAgeModifier() {
        return ageModifier;
    }

    public void setAgeModifier(String ageModifier) {
        this.ageModifier = ageModifier;
    }

    public String getWeightModifier() {
        return weightModifier;
    }

    public void setWeightModifier(String weightModifier) {
        this.weightModifier = weightModifier;
    }

    public String getHeightModifier() {
        return heightModifier;
    }

    public void setHeightModifier(String heightModifier) {
        this.heightModifier = heightModifier;
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

    @Override
    public String toString() {
        return getName();
    }

}
