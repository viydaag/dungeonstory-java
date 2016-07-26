package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@IdClass(CharacterClassId.class)
@Table(name = "CharacterClass")
public class CharacterClass implements Serializable {

    private static final long serialVersionUID = -2633155753784402849L;

    @Id
    @Column(name = "characterId")
    private Long characterId;

    @Id
    @Column(name = "classId")
    private Long classId;

    @Column(name = "classLevel")
    private int classLevel;

    @ManyToOne
    @JoinColumn(name = "characterId", updatable = false, insertable = false)
    private Character character;

    @ManyToOne
    @JoinColumn(name = "classId", updatable = false, insertable = false)
    private DSClass classe;

    public CharacterClass() {
        // TODO Auto-generated constructor stub
    }

    public Long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(Long characterId) {
        this.characterId = characterId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getClassLevel() {
        return classLevel;
    }

    public void setClassLevel(int classLevel) {
        this.classLevel = classLevel;
    }

    public DSClass getClasse() {
        return classe;
    }

    public void setClasse(DSClass classe) {
        this.classe = classe;
    }
}
