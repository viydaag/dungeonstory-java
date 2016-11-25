package com.dungeonstory.backend.data;

import static javax.persistence.LockModeType.READ;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.dungeonstory.backend.repository.DescriptiveEntity;

@Entity
@Table(name = "Feat")
@NamedQueries({
        @NamedQuery(name = Feat.FIND_ALL_FEATS, query = "SELECT e FROM Feat e WHERE e.isClassFeature = 0", lockMode = READ),
        @NamedQuery(name = Feat.FIND_ALL_FEATS_EXCEPT, query = "SELECT e FROM Feat e WHERE e.isClassFeature = 0 AND e.id != :featId", lockMode = READ),
        @NamedQuery(name = Feat.FIND_ALL_UNASSIGNED_FEATS, query = "SELECT e FROM Feat e WHERE e.isClassFeature = 0 AND e.id NOT IN (SELECT f.id FROM Character c JOIN c.feats f WHERE c.id = :characterId)", lockMode = READ),
        @NamedQuery(name = Feat.FIND_ALL_CLASS_FEATURES, query = "SELECT e FROM Feat e WHERE e.isClassFeature = 1", lockMode = READ),
        @NamedQuery(name = Feat.FIND_ALL_CLASS_FEATURES_WITHOUT_PARENT, query = "SELECT e FROM Feat e WHERE e.isClassFeature = 1 AND e.parent IS NULL ORDER BY e.name ASC", lockMode = READ),
        @NamedQuery(name = Feat.FIND_ALL_CLASS_FEATURE_EXCEPT, query = "SELECT e FROM Feat e WHERE e.isClassFeature = 1 AND e.id != :featId", lockMode = READ) })
public class Feat extends AbstractTimestampEntity implements DescriptiveEntity, Serializable {

    public static final String FIND_ALL_CLASS_FEATURE_EXCEPT          = "findAllClassFeatureExcept";
    public static final String FIND_ALL_CLASS_FEATURES_WITHOUT_PARENT = "findAllClassFeaturesWithoutParent";
    public static final String FIND_ALL_CLASS_FEATURES                = "findAllClassFeatures";
    public static final String FIND_ALL_UNASSIGNED_FEATS              = "findAllUnassignedFeats";
    public static final String FIND_ALL_FEATS_EXCEPT                  = "findAllFeatsExcept";
    public static final String FIND_ALL_FEATS                         = "findAllFeats";

    private static final long serialVersionUID = 291837938711381342L;

    public enum FeatUsage {
        PASSIVE, ACTION, ACTION_BONUS, REACTION
    }

    public enum PrerequisiteType {
        NONE, ARMOR_PROFICIENCY, ABILITY, CAST_SPELL
    }

    public enum RestType {
        SHORT("Court"), LONG("Long");

        private String value;

        private RestType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return getValue();
        }
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

    @ManyToOne(optional = true, cascade = { CascadeType.ALL })
    @JoinColumn(name = "parentId")
    private Feat parent;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "parent")
    private List<Feat> children;

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

    @Column(name = "nbUse")
    Integer nbUse;

    @Enumerated(EnumType.STRING)
    @Column(name = "restType")
    private RestType restType;

    @ManyToMany(mappedBy = "feats")
    private List<Character> characters;

    @OneToOne
    @JoinColumn(name = "replaceFeatId")
    private Feat replacement;

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

    @Override
    public String getDescription() {
        return description;
    }

    @Override
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

    public Feat getParent() {
        return parent;
    }

    public void setParent(Feat parent) {
        this.parent = parent;
    }

    public List<Feat> getChildren() {
        return children;
    }

    public void setChildren(List<Feat> children) {
        this.children = children;
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

    public Integer getNbUse() {
        return nbUse;
    }

    public void setNbUse(Integer nbUse) {
        this.nbUse = nbUse;
    }

    public RestType getRestType() {
        return restType;
    }

    public void setRestType(RestType restType) {
        this.restType = restType;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public Feat getReplacement() {
        return replacement;
    }

    public void setReplacement(Feat replacement) {
        this.replacement = replacement;
    }

    @Override
    public String toString() {
        return getName();
    }

}
