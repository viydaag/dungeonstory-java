package com.dungeonstory.backend.data;

import static javax.persistence.LockModeType.READ;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

import com.dungeonstory.backend.repository.DescriptiveEntity;

@Entity
@Table(name = "Feat")
@NamedQueries({
        @NamedQuery(name = Feat.FIND_ALL_FEATS_EXCEPT, query = "SELECT e FROM Feat e WHERE e.id != :featId", lockMode = READ),
        @NamedQuery(name = Feat.FIND_ALL_UNASSIGNED_FEATS, query = "SELECT e FROM Feat e WHERE e.id NOT IN (SELECT f.id FROM Character c JOIN c.feats f WHERE c.id = :characterId)", lockMode = READ)})
public class Feat extends AbstractTimestampEntity implements DescriptiveEntity, Serializable {

    public static final String FIND_ALL_UNASSIGNED_FEATS              = "findAllUnassignedFeats";
    public static final String FIND_ALL_FEATS_EXCEPT                  = "findAllFeatsExcept";

    private static final long serialVersionUID = 291837938711381342L;

    public enum FeatUsage {
        PASSIVE, ACTION, ACTION_BONUS, REACTION
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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "prerequisiteType", nullable = false)
    private PrerequisiteType prerequisiteType = PrerequisiteType.NONE;

    @Column(name = "prerequisiteArmorProficiency")
    @Enumerated(EnumType.STRING)
    private ArmorType.ProficiencyType prerequisiteArmorProficiency;

    @ManyToOne
    @JoinFetch(JoinFetchType.OUTER)
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
