package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class CharacterClass implements Serializable {

    private static final long serialVersionUID = -2633155753784402849L;

    @Id
    @ManyToOne
    @JoinColumn(name = "characterId")
    private Character character;

    @Id
    @ManyToOne
    @JoinColumn(name = "classId")
    private DSClass classe;
    
    @NotNull
    @Column(name = "classLevel")
    private int classLevel;

    @ManyToMany
    @JoinTable(name = "CharacterClassKnownSpells", joinColumns = {
            @JoinColumn(name = "characterId", referencedColumnName = "characterId"),
            @JoinColumn(name = "classId", referencedColumnName = "classId") }, inverseJoinColumns = @JoinColumn(name = "spellId", referencedColumnName = "id"))
    private List<Spell> knownSpells;

    @ManyToMany
    @JoinTable(name = "CharacterClassPreparedSpells", joinColumns = {
            @JoinColumn(name = "characterId", referencedColumnName = "characterId"),
            @JoinColumn(name = "classId", referencedColumnName = "classId") }, inverseJoinColumns = @JoinColumn(name = "spellId", referencedColumnName = "id"))
    private List<Spell> preparedSpells;

    @Min(value = 0)
    @Column(name = "nbPreparedSpells")
    private Integer nbPreparedSpells;

    public CharacterClass() {
        super();
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

    @Override
    public String toString() {
        return classe.toString() + " (" + classLevel + ")";
    }
}
