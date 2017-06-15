package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.PrivateOwned;

@Entity
@Table(name = "ClassSpecialization")
public class ClassSpecialization extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = -2465166155999242520L;
    
    @NotNull
    @Column(name = "name", unique = true, nullable = false)
    private String name;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "classId", nullable = false)
    private DSClass parentClass;
    
    @Column(name = "isSpellCasting")
    private boolean isSpellCasting = false;

    @ManyToOne
    @JoinColumn(name = "spellCasingAbilityId")
    private Ability spellCastingAbility;

    @OneToMany(mappedBy = "classSpec", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @PrivateOwned
    private List<ClassSpecLevelFeature> classSpecFeatures;
    
    @OneToMany(mappedBy = "classSpec", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @PrivateOwned
    private List<ClassSpecializationSpellSlots> spellSlots;

    @OneToMany(mappedBy = "classSpec", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @PrivateOwned
    private List<ClassSpecLevelSpell> classSpecSpells;

    public ClassSpecialization() {
        super();
        classSpecFeatures = new ArrayList<ClassSpecLevelFeature>();
        spellSlots = new ArrayList<ClassSpecializationSpellSlots>();
        classSpecSpells = new ArrayList<ClassSpecLevelSpell>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DSClass getParentClass() {
        return parentClass;
    }

    public void setParentClass(DSClass parentClass) {
        this.parentClass = parentClass;
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

    public List<ClassSpecLevelFeature> getClassSpecFeatures() {
        return classSpecFeatures;
    }

    public void setClassSpecFeatures(List<ClassSpecLevelFeature> classSpecFeatures) {
        this.classSpecFeatures = classSpecFeatures;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ClassSpecializationSpellSlots> getSpellSlots() {
        return spellSlots;
    }

    public void setSpellSlots(List<ClassSpecializationSpellSlots> spellSlots) {
        this.spellSlots = spellSlots;
    }

    public List<ClassSpecLevelSpell> getClassSpecSpells() {
        return classSpecSpells;
    }

    public void setClassSpecSpells(List<ClassSpecLevelSpell> classSpecSpells) {
        this.classSpecSpells = classSpecSpells;
    }

    public void setSpellCasting(boolean isSpellCasting) {
        this.isSpellCasting = isSpellCasting;
    }

    @Override
    public String toString() {
        return getName();
    }

}
