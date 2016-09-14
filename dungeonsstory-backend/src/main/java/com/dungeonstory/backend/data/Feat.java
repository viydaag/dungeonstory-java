package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Feat")
public class Feat extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 291837938711381342L;

    public enum FeatUsage {
        PASSIVE, ACTION, REACTION
    }
    
    public enum PrerequisiteType {
        NONE, ARMOR_PROFICIENCY, ABILITY, CAST_SPELL
    }

    @NotNull
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "featUsage", nullable = false)
    private FeatUsage usage;
    
    @Column(name = "isClassFeature")
    private boolean isClassFeature = false;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "prerequisiteType", nullable = false)
    private PrerequisiteType prerequisiteType = PrerequisiteType.NONE;
    
    @Column(name = "prerequisiteArmorProficiency")
    @Enumerated(EnumType.STRING)
    private ArmorType.ProficiencyType prerequisiteArmorProficiency;
    
    @ManyToOne
    @JoinColumn(name = "prerequisiteAbilityId")
    private Ability prerequisiteAbility;
    
    @Column(name = "prerequisiteAbilityScore")
    private Integer prerequisiteAbilityScore;

    @ManyToMany(mappedBy = "feats")
    private List<Character> characters;

    public Feat() {
        super();
    }

    public Feat(String name, String description, FeatUsage type) {
        super();
        this.name = name;
        this.description = description;
        this.usage = type;
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

    public FeatUsage getUsage() {
        return usage;
    }

    public void setUsage(FeatUsage usage) {
        this.usage = usage;
    }

    public boolean getIsClassFeature() {
        return isClassFeature;
    }

    public void setIsClassFeature(boolean isClassFeature) {
        this.isClassFeature = isClassFeature;
    }

    public PrerequisiteType getPrerequisiteType() {
        return prerequisiteType;
    }

    public void setPrerequisiteType(PrerequisiteType prerequisiteType) {
        this.prerequisiteType = prerequisiteType;
    }

    public ArmorType.ProficiencyType getPrerequisiteArmorProficiency() {
        return prerequisiteArmorProficiency;
    }

    public void setPrerequisiteArmorProficiency(ArmorType.ProficiencyType prerequisiteArmorProficiency) {
        this.prerequisiteArmorProficiency = prerequisiteArmorProficiency;
    }

    public Ability getPrerequisiteAbility() {
        return prerequisiteAbility;
    }

    public void setPrerequisiteAbility(Ability prerequisiteAbility) {
        this.prerequisiteAbility = prerequisiteAbility;
    }

    public Integer getPrerequisiteAbilityScore() {
        return prerequisiteAbilityScore;
    }

    public void setPrerequisiteAbilityScore(Integer prerequisiteAbilityScore) {
        this.prerequisiteAbilityScore = prerequisiteAbilityScore;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    @Override
    public String toString() {
        return getName();
    }

}
