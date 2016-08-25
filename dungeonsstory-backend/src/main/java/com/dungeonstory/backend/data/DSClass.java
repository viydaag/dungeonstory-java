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
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.PrivateOwned;

@Entity
@Table(name = "Class")
@NamedQuery(name = DSClass.deleteClassLevelBonusFeat, query = "DELETE FROM ClassLevelBonusFeat c WHERE c.classe = :classe AND c.level = :level AND c.feat = :feat")
public class DSClass extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 4948845539537092288L;
    
    public static final String deleteClassLevelBonusFeat =  "Class.deleteClassLevelBonusFeat";

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "shortDescription")
    private String shortDescription;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Min(value = 0)
    @Column(name = "lifePointPerLevel", nullable = false)
    private int lifePointPerLevel;
    
    @ManyToMany
    @JoinTable(name = "ClassSavingThrowProficiencies", joinColumns = {
        @JoinColumn(name = "classId", referencedColumnName = "id") }, 
            inverseJoinColumns = { @JoinColumn(name = "abilityId", referencedColumnName = "id") })
    @PrivateOwned
    private Set<Ability> savingThrowProficiencies;
    
    @ElementCollection(targetClass = ArmorType.ProficiencyType.class)
    @CollectionTable(name = "ClassArmorProficiencies", joinColumns = @JoinColumn(name = "classId", nullable = false))
    @Column(name = "proficiency", nullable = false)
    @Enumerated(EnumType.STRING)
    @PrivateOwned
    private Set<ArmorType.ProficiencyType> armorProficiencies;
    
    @ManyToMany
    @JoinTable(name = "ClassWeaponProficiencies", joinColumns = {
        @JoinColumn(name = "classId", referencedColumnName = "id") }, 
            inverseJoinColumns = { @JoinColumn(name = "weaponTypeId", referencedColumnName = "id") })
    private Set<WeaponType> weaponProficiencies;

    @ManyToMany
    @JoinTable(name = "ClassSkill", joinColumns = {
        @JoinColumn(name = "classId", referencedColumnName = "id") }, 
            inverseJoinColumns = { @JoinColumn(name = "skillId", referencedColumnName = "id") })
    private Set<Skill> baseSkills;

    @OneToMany(mappedBy = "classe", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @PrivateOwned   //means that a class level bonus will be deleted if not attached to a class
    private List<ClassLevelBonus> levelBonuses;
    
    @OneToMany(mappedBy = "classe", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @PrivateOwned   //means that a class level bonus feat will be deleted if not attached to a class
    private List<ClassLevelBonusFeat> featBonuses;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "ClassSpell", joinColumns = {
        @JoinColumn(name = "classId", referencedColumnName = "id") }, 
            inverseJoinColumns = { @JoinColumn(name = "spellId", referencedColumnName = "id") })
    private Set<Spell> spells;

    public DSClass() {
        savingThrowProficiencies = new HashSet<Ability>();
        armorProficiencies = new HashSet<ArmorType.ProficiencyType>();
        weaponProficiencies = new HashSet<WeaponType>();
        baseSkills = new HashSet<Skill>();
        levelBonuses = new ArrayList<ClassLevelBonus>();
        featBonuses = new ArrayList<ClassLevelBonusFeat>();
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

    public List<ClassLevelBonus> getLevelBonuses() {
        return levelBonuses;
    }

    public void setLevelBonuses(List<ClassLevelBonus> levelBonuses) {
        this.levelBonuses = levelBonuses;
    }

    public List<ClassLevelBonusFeat> getFeatBonuses() {
        return featBonuses;
    }

    public void setFeatBonuses(List<ClassLevelBonusFeat> featBonuses) {
        this.featBonuses = featBonuses;
    }

    public Set<Spell> getSpells() {
        return spells;
    }

    public void setSpells(Set<Spell> spells) {
        this.spells = spells;
    }

    @Override
    public String toString() {
        return getName();
    }

}
