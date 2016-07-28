package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

    public CharacterClass() {
        // TODO Auto-generated constructor stub
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
}
