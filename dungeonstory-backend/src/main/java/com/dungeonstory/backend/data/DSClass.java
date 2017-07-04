package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;
import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;
import org.eclipse.persistence.annotations.PrivateOwned;

import com.dungeonstory.backend.data.Tool.ToolType;

@Entity
@Table(name = "Class")
public class DSClass extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 4948845539537092288L;
    
    public enum SpellCastingType {
        KNOWN("Inné"), PREPARED("Préparé");

        private String name;

        private SpellCastingType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return getName();
        }

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
    @Column(name = "lifePointPerLevel", nullable = false)
    private int lifePointPerLevel;
    
    @Column(name = "isSpellCasting")
    private boolean isSpellCasting;
    
    @ManyToOne
    @JoinFetch(JoinFetchType.OUTER)
    @JoinColumn(name = "spellCastingAbilityId")
    private Ability spellCastingAbility;

    @Column(name = "spellCastingType")
    @Enumerated(EnumType.STRING)
    private SpellCastingType spellCastingType;

    @ManyToMany
    @BatchFetch(value = BatchFetchType.JOIN)
    @JoinTable(name = "ClassSavingThrowProficiencies", joinColumns = {
        @JoinColumn(name = "classId", referencedColumnName = "id") }, 
            inverseJoinColumns = { @JoinColumn(name = "abilityId", referencedColumnName = "id") })
    @PrivateOwned
    private Set<Ability> savingThrowProficiencies;
    
    @ElementCollection(targetClass = ArmorType.ProficiencyType.class)
    @CollectionTable(name = "ClassArmorProficiencies", joinColumns = @JoinColumn(name = "classId", nullable = false))
    @BatchFetch(value = BatchFetchType.JOIN)
    @Column(name = "proficiency", nullable = false)
    @Enumerated(EnumType.STRING)
    @PrivateOwned
    private Set<ArmorType.ProficiencyType> armorProficiencies;
    
    @ManyToMany
    @BatchFetch(value = BatchFetchType.JOIN)
    @JoinTable(name = "ClassWeaponProficiencies", joinColumns = {
        @JoinColumn(name = "classId", referencedColumnName = "id") }, 
            inverseJoinColumns = { @JoinColumn(name = "weaponTypeId", referencedColumnName = "id") })
    private Set<WeaponType> weaponProficiencies;
    
    @ElementCollection(targetClass = ToolType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "ClassToolProficiencies", joinColumns = @JoinColumn(name = "classId", nullable = false))
    @BatchFetch(value = BatchFetchType.JOIN)
    @Column(name = "toolType", nullable = false)
    @PrivateOwned
    private Set<ToolType> toolProficiencies;
    
    @Min(value = 0)
    @Column(name = "nbChosenSkills")
    private int nbChosenSkills = 0;

    @ManyToMany
    @BatchFetch(value = BatchFetchType.JOIN)
    @JoinTable(name = "ClassSkill", joinColumns = {
        @JoinColumn(name = "classId", referencedColumnName = "id") }, 
            inverseJoinColumns = { @JoinColumn(name = "skillId", referencedColumnName = "id") })
    private Set<Skill> baseSkills;

    @OneToMany(mappedBy = "classe", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @BatchFetch(value = BatchFetchType.JOIN)
    @PrivateOwned   //means that a class level bonus will be deleted if not attached to a class
    private List<ClassLevelBonus> levelBonuses;
    
    @OneToMany(mappedBy = "classe", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @BatchFetch(value = BatchFetchType.JOIN)
    @PrivateOwned   //means that a class level feature will be deleted if not attached to a class
    private List<ClassLevelFeature> classFeatures;
    
    @OneToMany(mappedBy = "parentClass", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE })
    @BatchFetch(value = BatchFetchType.JOIN)
    @PrivateOwned
    private Set<ClassSpecialization> classSpecs;
    
    @OneToMany(mappedBy = "classe", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @BatchFetch(value = BatchFetchType.JOIN)
    @PrivateOwned
    private List<ClassSpellSlots> spellSlots;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @BatchFetch(value = BatchFetchType.JOIN)
    @JoinTable(name = "ClassSpell", joinColumns = {
        @JoinColumn(name = "classId", referencedColumnName = "id") }, 
            inverseJoinColumns = { @JoinColumn(name = "spellId", referencedColumnName = "id") })
    private Set<Spell> spells;
    
    @OneToMany(mappedBy = "classe", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @BatchFetch(value = BatchFetchType.JOIN)
    @PrivateOwned
    private List<ClassEquipment> startingEquipment;
    
    @NotNull
    @Min(value = 0)
    @Column(name = "startingGold", nullable = false)
    private int startingGold = 0;

    public DSClass() {
        savingThrowProficiencies = new HashSet<Ability>();
        armorProficiencies = new HashSet<ArmorType.ProficiencyType>();
        weaponProficiencies = new HashSet<WeaponType>();
        baseSkills = new HashSet<Skill>();
        levelBonuses = new ArrayList<ClassLevelBonus>();
        classFeatures = new ArrayList<ClassLevelFeature>();
        classSpecs = new HashSet<ClassSpecialization>();
        startingEquipment = new ArrayList<ClassEquipment>();
        spellSlots = new ArrayList<ClassSpellSlots>();
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

    public int getLifePointPerLevel() {
        return lifePointPerLevel;
    }

    public void setLifePointPerLevel(int lifePointPerLevel) {
        this.lifePointPerLevel = lifePointPerLevel;
    }

    public boolean getIsSpellCasting() {
        return isSpellCasting;
    }

    public void setIsSpellCasting(boolean isSpellCasting) {
        this.isSpellCasting = isSpellCasting;
    }

    public Ability getSpellCastingAbility() {
        return spellCastingAbility;
    }

    public void setSpellCastingAbility(Ability spellCastingAbility) {
        this.spellCastingAbility = spellCastingAbility;
    }

    public SpellCastingType getSpellCastingType() {
        return spellCastingType;
    }

    public void setSpellCastingType(SpellCastingType spellCastingType) {
        this.spellCastingType = spellCastingType;
    }

    public List<ClassSpellSlots> getSpellSlots() {
        return spellSlots;
    }

    public void setSpellSlots(List<ClassSpellSlots> spellSlots) {
        this.spellSlots = spellSlots;
    }

    public int getNbChosenSkills() {
        return nbChosenSkills;
    }

    public void setNbChosenSkills(int nbChosenSkills) {
        this.nbChosenSkills = nbChosenSkills;
    }

    public Set<Skill> getBaseSkills() {
        return baseSkills;
    }

    public void setBaseSkills(Set<Skill> baseSkills) {
        this.baseSkills = baseSkills;
    }

    public Set<Ability> getSavingThrowProficiencies() {
        return savingThrowProficiencies;
    }

    public void setSavingThrowProficiencies(Set<Ability> savingThrowProficiencies) {
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

    public Set<ToolType> getToolProficiencies() {
        return toolProficiencies;
    }

    public void setToolProficiencies(Set<ToolType> toolProficiencies) {
        this.toolProficiencies = toolProficiencies;
    }

    public List<ClassLevelBonus> getLevelBonuses() {
        return levelBonuses;
    }

    public void setLevelBonuses(List<ClassLevelBonus> levelBonuses) {
        this.levelBonuses = levelBonuses;
    }

    public List<ClassLevelFeature> getClassFeatures() {
        return classFeatures;
    }

    public void setClassFeatures(List<ClassLevelFeature> classFeatures) {
        this.classFeatures = classFeatures;
    }

    public Set<ClassSpecialization> getClassSpecs() {
        return classSpecs;
    }

    public void setClassSpecs(Set<ClassSpecialization> classSpecs) {
        this.classSpecs = classSpecs;
    }

    public Set<Spell> getSpells() {
        return spells;
    }

    public void setSpells(Set<Spell> spells) {
        this.spells = spells;
    }

    public int getStartingGold() {
        return startingGold;
    }

    public void setStartingGold(int startingGold) {
        this.startingGold = startingGold;
    }

    public List<ClassEquipment> getStartingEquipment() {
        return startingEquipment;
    }

    public void setStartingEquipment(List<ClassEquipment> startingEquipment) {
        this.startingEquipment = startingEquipment;
    }

    @Override
    public String toString() {
        return getName();
    }

}
