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

    private static final long serialVersionUID = 1L;

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

    //TODO
    //attacks
    //senses

	public Monster() {
		super();
	}   
   
}
