package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.PrivateOwned;

@Entity
@Table(name = "Spell")

@NamedQueries({
        @NamedQuery(name = Spell.ALL_KNOWN_CLASS_SPELLS_BY_LEVEL, query = "SELECT s FROM DSClass c JOIN c.spells s WHERE s.level = :level AND c.id = :classId AND s.id IN (SELECT sp.id FROM Character ch JOIN ch.classes cl JOIN cl.knownSpells sp WHERE ch.id = :characterId AND cl.classe.id = :classId)"),
        @NamedQuery(name = Spell.ALL_UNKNOWN_CLASS_SPELLS_BY_LEVEL, query = "SELECT s FROM DSClass c JOIN c.spells s WHERE s.level = :level AND c.id = :classId AND s.id NOT IN (SELECT sp.id FROM Character ch JOIN ch.classes cl JOIN cl.knownSpells sp WHERE ch.id = :characterId AND cl.classe.id = :classId)"),
        @NamedQuery(name = Spell.ALL_SPELLS_BY_LEVEL, query = "SELECT s FROM Spell s WHERE s.level = :level"),
        @NamedQuery(name = Spell.ALL_CLASS_SPELLS_BY_LEVEL, query = "SELECT s FROM DSClass c JOIN c.spells s WHERE c.id = :classId AND s.level = :level"),
        @NamedQuery(name = Spell.ALL_SPELLS_SORTED_BY_LEVEL_AND_NAME, query = "SELECT e FROM Spell e ORDER BY e.level ASC, e.name ASC") })
public class Spell extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = -981852238942809050L;
    
    public static final String ALL_KNOWN_CLASS_SPELLS_BY_LEVEL = "findAllKnownClassSpellsByLevel";
    public static final String ALL_UNKNOWN_CLASS_SPELLS_BY_LEVEL = "findAllUnknownClassSpellsByLevel";
    public static final String ALL_SPELLS_BY_LEVEL               = "findAllSpellsByLevel";
    public static final String ALL_CLASS_SPELLS_BY_LEVEL         = "findAllClassSpellsByLevel";
    public static final String ALL_SPELLS_SORTED_BY_LEVEL_AND_NAME = "findAllSpellsSortedByLevelAndName";

    public static final int CANTRIP_LEVEL   = 0;
    public static final int MIN_SPELL_LEVEL = 1;
    public static final int MAX_SPELL_LEVEL = 9;

    public enum MagicSchool {
        ABJURATION,
        CONJURATION,
        DIVINATION,
        ENCHANTEMENT,
        EVOCATION,
        ILLUSION,
        NECROMANCIE,
        TRANSMUTATION
    }
    
    public enum ComponentType {
        V("Verbal"),
        G("Gestuel"),
        M("Materiel");
        
        private String name;
        
        private ComponentType(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    
    public enum CastingTime {
        ACTION,
        BONUS_ACTION,
        TIME
    }
    
    public enum TimeUnit {
        MINUTE,
        HOUR,
        DAY
    }
    
    public enum DurationType {
        INSTANTANEOUS,
        CONCENTRATION,
        TIME,
        PERMANENT
    }
    
    public enum Target {
        SELF,
        CREATURE,
        OBJECT,
        POINT
    }
    
    public enum RangeType {
        SELF,
        TOUCH,
        DISTANCE
    }
    
    public enum AreaOfEffect {
        CONE,
        CUBE,
        CYLINDER,
        LINE,
        SPHERE
    }
    
    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @NotNull
    @Column(name = "level", nullable = false)
    private Integer level;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "school", nullable = false)
    private MagicSchool school;
    
    @ElementCollection(targetClass = ComponentType.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "SpellComponentType", joinColumns = @JoinColumn(name = "spellId", nullable = false))
    @Column(name = "componentType", nullable = false)
    private Set<ComponentType> componentTypes;
    
    @ManyToMany
    @JoinTable(
        name="SpellComponent",
        joinColumns={@JoinColumn(name="spellId", referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="equipmentId", referencedColumnName="id")})
    private List<Equipment> components;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "castingTime", nullable = false)
    private CastingTime castingTime;
    
    @Column(name = "castingTimeValue")
    private Integer castingTimeValue;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "castingTimeUnit")
    private TimeUnit castingTimeUnit;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "duration", nullable = false)
    private DurationType duration;
    
    @Column(name = "durationTimeValue")
    private Integer durationTimeValue;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "durationTimeUnit")
    private TimeUnit durationTimeUnit;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "target", nullable = false)
    private Target target;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "areaOfEffect")
    private AreaOfEffect areaOfEffect;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "rangeType", nullable = false)
    private RangeType range;
    
    @Column(name = "rangeValueInFeet")
    private Integer rangeValueInFeet;

    @ManyToOne
    @JoinColumn(name = "savingThrowAbilityId")
    private Ability savingThrowAbility;
    
    @Column(name = "attackRoll")
    private boolean attackRoll;
    
    @Column(name = "higherLevel")
    private boolean higherLevel;
    
    @OneToMany(mappedBy = "spell", cascade = {CascadeType.ALL})
    @PrivateOwned
    private List<SpellEffect> effects;

    public Spell() {
        super();
        effects = new ArrayList<SpellEffect>();
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public MagicSchool getSchool() {
        return school;
    }

    public void setSchool(MagicSchool school) {
        this.school = school;
    }

    public Set<ComponentType> getComponentTypes() {
        return componentTypes;
    }

    public void setComponentTypes(Set<ComponentType> componentTypes) {
        this.componentTypes = componentTypes;
    }

    public List<Equipment> getComponents() {
        return components;
    }

    public void setComponents(List<Equipment> components) {
        this.components = components;
    }

    public CastingTime getCastingTime() {
        return castingTime;
    }

    public void setCastingTime(CastingTime castingTime) {
        this.castingTime = castingTime;
    }

    public Integer getCastingTimeValue() {
        return castingTimeValue;
    }

    public void setCastingTimeValue(Integer castingTimeValue) {
        this.castingTimeValue = castingTimeValue;
    }

    public TimeUnit getCastingTimeUnit() {
        return castingTimeUnit;
    }

    public void setCastingTimeUnit(TimeUnit castingTimeUnit) {
        this.castingTimeUnit = castingTimeUnit;
    }

    public DurationType getDuration() {
        return duration;
    }

    public void setDuration(DurationType duration) {
        this.duration = duration;
    }

    public Integer getDurationTimeValue() {
        return durationTimeValue;
    }

    public void setDurationTimeValue(Integer durationTimeValue) {
        this.durationTimeValue = durationTimeValue;
    }

    public TimeUnit getDurationTimeUnit() {
        return durationTimeUnit;
    }

    public void setDurationTimeUnit(TimeUnit durationTimeUnit) {
        this.durationTimeUnit = durationTimeUnit;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target) {
        this.target = target;
    }

    public AreaOfEffect getAreaOfEffect() {
        return areaOfEffect;
    }

    public void setAreaOfEffect(AreaOfEffect areaOfEffect) {
        this.areaOfEffect = areaOfEffect;
    }

    public RangeType getRange() {
        return range;
    }

    public void setRange(RangeType range) {
        this.range = range;
    }

    public Integer getRangeValueInFeet() {
        return rangeValueInFeet;
    }

    public void setRangeValueInFeet(Integer rangeValueInFeet) {
        this.rangeValueInFeet = rangeValueInFeet;
    }

    public Ability getSavingThrowAbility() {
        return savingThrowAbility;
    }

    public void setSavingThrowAbility(Ability savingThrowAbility) {
        this.savingThrowAbility = savingThrowAbility;
    }

    public boolean isAttackRoll() {
        return attackRoll;
    }

    public void setAttackRoll(boolean attackRoll) {
        this.attackRoll = attackRoll;
    }

    public boolean isHigherLevel() {
        return higherLevel;
    }

    public void setHigherLevel(boolean higherLevel) {
        this.higherLevel = higherLevel;
    }

    public List<SpellEffect> getEffects() {
        return effects;
    }

    public void setEffects(List<SpellEffect> effects) {
        this.effects = effects;
    }
    
    @Override
    public String toString() {
        return getName();
    }

}
