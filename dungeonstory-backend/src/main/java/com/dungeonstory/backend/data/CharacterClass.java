package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(CharacterClassId.class)
@Table(name = "CharacterClass")
public class CharacterClass
        implements Serializable, Cloneable {

    private static final long serialVersionUID = -2633155753784402849L;

    @Id
    @ManyToOne
    @JoinColumn(name = "characterId", updatable = false, nullable = false)
    private Character character;

    @Id
    @ManyToOne
    @JoinColumn(name = "classId", updatable = false, nullable = false)
    private DSClass classe;

    @NotNull
    @Column(name = "classLevel")
    private int classLevel;

    @ManyToMany
    @JoinTable(name = "CharacterClassFeature", joinColumns = { @JoinColumn(name = "characterId", referencedColumnName = "characterId"),
            @JoinColumn(name = "classId", referencedColumnName = "classId") }, inverseJoinColumns = {
                    @JoinColumn(name = "featureId", referencedColumnName = "id") })
    // These are the assigned character features for that class
    private Set<ClassFeature> classFeatures;

    @ManyToMany
    @JoinTable(name = "CharacterClassKnownSpells", joinColumns = { @JoinColumn(name = "characterId", referencedColumnName = "characterId"),
            @JoinColumn(name = "classId", referencedColumnName = "classId") }, inverseJoinColumns = @JoinColumn(name = "spellId", referencedColumnName = "id"))
    private List<Spell> knownSpells;

    @ManyToMany
    @JoinTable(name = "CharacterClassPreparedSpells", joinColumns = { @JoinColumn(name = "characterId", referencedColumnName = "characterId"),
            @JoinColumn(name = "classId", referencedColumnName = "classId") }, inverseJoinColumns = @JoinColumn(name = "spellId", referencedColumnName = "id"))
    private List<Spell> preparedSpells;

    @Min(value = 0)
    @Column(name = "nbPreparedSpells")
    private Integer nbPreparedSpells;

    @ManyToOne
    @JoinColumn(name = "classSpecId")
    // This is the specialization the character has chosen for this class.
    private ClassSpecialization classSpecialization;

    public CharacterClass() {
        super();
        classFeatures = new HashSet<>();
        knownSpells = new ArrayList<Spell>();
        preparedSpells = new ArrayList<Spell>();
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public DSClass getClasse() {
        return classe;
    }

    public void setClasse(DSClass classe) {
        this.classe = classe;
    }

    public int getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(int classLevel) {
        this.classLevel = classLevel;
    }

    public Set<ClassFeature> getClassFeatures() {
        return classFeatures;
    }

    public void setClassFeatures(Set<ClassFeature> classFeatures) {
        this.classFeatures = classFeatures;
    }

    public List<Spell> getKnownSpells() {
        return knownSpells;
    }

    public void setKnownSpells(List<Spell> knownSpells) {
        this.knownSpells = knownSpells;
    }

    public List<Spell> getPreparedSpells() {
        return preparedSpells;
    }

    public void setPreparedSpells(List<Spell> preparedSpells) {
        this.preparedSpells = preparedSpells;
    }

    public Integer getNbPreparedSpells() {
        return nbPreparedSpells;
    }

    public void setNbPreparedSpells(Integer nbPreparedSpells) {
        this.nbPreparedSpells = nbPreparedSpells;
    }

    public ClassSpecialization getClassSpecialization() {
        return classSpecialization;
    }

    public void setClassSpecialization(ClassSpecialization classSpecialization) {
        this.classSpecialization = classSpecialization;
    }

    @Override
    public String toString() {
        return classe.toString() + " (" + classLevel + ")";
    }

    @Override
    public CharacterClass clone() {
        try {
            CharacterClass cc = (CharacterClass) super.clone();
            cc.setClassFeatures(cc.getClassFeatures().stream().map(ClassFeature::clone).collect(Collectors.toSet()));
            return cc;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
