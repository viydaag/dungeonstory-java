package com.dungeonstory.backend.data;

import java.io.Serializable;
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

/**
 * Entity implementation class for Entity: Monster
 *
 */
@Entity
@Table(name = "Monster")
public class Monster extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = -4897720435357339184L;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
	private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "size", nullable = false)
    private CreatureSize size;

    @Column(name = "tag")
    private String tag;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "alignmentId", nullable = false)
    private Alignment alignment;

    @Column(name = "armorClass")
    private int armorClass;

    @Column(name = "hitPoints")
    private int hitPoints;

    @Min(value = 0)
    @Column(name = "groundSpeed")
    private Integer groundSpeed;

    @Min(value = 0)
    @Column(name = "burrowSpeed")
    private Integer burrowSpeed;

    @Min(value = 0)
    @Column(name = "climbSpeed")
    private Integer climbSpeed;

    @Min(value = 0)
    @Column(name = "flySpeed")
    private Integer flySpeed;

    @Min(value = 0)
    @Column(name = "swimSpeed")
    private Integer swimSpeed;

    @NotNull
    @Min(value = 1)
    @Column(name = "strength", nullable = false)
    private int strength;

    @NotNull
    @Min(value = 1)
    @Column(name = "dexterity", nullable = false)
    private int dexterity;

    @NotNull
    @Min(value = 1)
    @Column(name = "constitution", nullable = false)
    private int constitution;

    @NotNull
    @Min(value = 1)
    @Column(name = "intelligence", nullable = false)
    private int intelligence;

    @NotNull
    @Min(value = 1)
    @Column(name = "wisdom", nullable = false)
    private int wisdom;

    @NotNull
    @Min(value = 1)
    @Column(name = "charisma", nullable = false)
    private int charisma;

    @NotNull
    @Min(value = 0)
    @Column(name = "passivePerception", nullable = false)
    private int passivePerception = 0;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "challengeRatingId", nullable = false)
    private ChallengeRating challengeRating;

    @ManyToMany
    @JoinTable(name = "MonsterDamageVulnerability", joinColumns = {
            @JoinColumn(name = "monsterId", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "damageTypeId", referencedColumnName = "id") })
    private List<DamageType> damageVulnerabilities;

    @ManyToMany
    @JoinTable(name = "MonsterDamageResistance", joinColumns = {
            @JoinColumn(name = "monsterId", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "damageTypeId", referencedColumnName = "id") })
    private List<DamageType> damageResistances;

    @ManyToMany
    @JoinTable(name = "MonsterDamageImmunity", joinColumns = {
            @JoinColumn(name = "monsterId", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "damageTypeId", referencedColumnName = "id") })
    private List<DamageType> damageImmunities;

    @ElementCollection(targetClass = Condition.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "MonsterConditionImmunity", joinColumns = @JoinColumn(name = "monster", nullable = false))
    @Column(name = "conditionName", nullable = false)
    private List<Condition> conditionImmunities;

    @ManyToMany
    @JoinTable(name = "MonsterLanguage", joinColumns = {
            @JoinColumn(name = "monsterId", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "languageId", referencedColumnName = "id") })
    private Set<Language> languages;

    @OneToMany(mappedBy = "monster", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private List<MonsterSavingThrow> savingThrows;

    @OneToMany(mappedBy = "monster", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    private List<MonsterSkill> skills;

    @OneToMany(mappedBy = "monster", cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
    private List<MonsterAttack> attacks;

    @OneToMany(mappedBy = "monster", cascade = { CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE })
    private List<MonsterSense> senses;

	public Monster() {
		super();
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

    public CreatureSize getSize() {
        return size;
    }

    public void setSize(CreatureSize size) {
        this.size = size;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public Integer getGroundSpeed() {
        return groundSpeed;
    }

    public void setGroundSpeed(Integer groundSpeed) {
        this.groundSpeed = groundSpeed;
    }

    public Integer getBurrowSpeed() {
        return burrowSpeed;
    }

    public void setBurrowSpeed(Integer burrowSpeed) {
        this.burrowSpeed = burrowSpeed;
    }

    public Integer getClimbSpeed() {
        return climbSpeed;
    }

    public void setClimbSpeed(Integer climbSpeed) {
        this.climbSpeed = climbSpeed;
    }

    public Integer getFlySpeed() {
        return flySpeed;
    }

    public void setFlySpeed(Integer flySpeed) {
        this.flySpeed = flySpeed;
    }

    public Integer getSwimSpeed() {
        return swimSpeed;
    }

    public void setSwimSpeed(Integer swimSpeed) {
        this.swimSpeed = swimSpeed;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public ChallengeRating getChallengeRating() {
        return challengeRating;
    }

    public void setChallengeRating(ChallengeRating challengeRating) {
        this.challengeRating = challengeRating;
    }

    public List<DamageType> getDamageVulnerabilities() {
        return damageVulnerabilities;
    }

    public void setDamageVulnerabilities(List<DamageType> damageVulnerabilities) {
        this.damageVulnerabilities = damageVulnerabilities;
    }

    public List<DamageType> getDamageResistances() {
        return damageResistances;
    }

    public void setDamageResistances(List<DamageType> damageResistances) {
        this.damageResistances = damageResistances;
    }

    public List<DamageType> getDamageImmunities() {
        return damageImmunities;
    }

    public void setDamageImmunities(List<DamageType> damageImmunities) {
        this.damageImmunities = damageImmunities;
    }

    public List<Condition> getConditionImmunities() {
        return conditionImmunities;
    }

    public void setConditionImmunities(List<Condition> conditionImmunities) {
        this.conditionImmunities = conditionImmunities;
    }

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public List<MonsterSavingThrow> getSavingThrows() {
        return savingThrows;
    }

    public void setSavingThrows(List<MonsterSavingThrow> savingThrows) {
        this.savingThrows = savingThrows;
    }

    public List<MonsterSkill> getSkills() {
        return skills;
    }

    public void setSkills(List<MonsterSkill> skills) {
        this.skills = skills;
    }

    public List<MonsterAttack> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<MonsterAttack> attacks) {
        this.attacks = attacks;
    }

    public int getPassivePerception() {
        return passivePerception;
    }

    public void setPassivePerception(int passivePerception) {
        this.passivePerception = passivePerception;
    }

    public List<MonsterSense> getSenses() {
        return senses;
    }

    public void setSenses(List<MonsterSense> senses) {
        this.senses = senses;
    }
   
}
