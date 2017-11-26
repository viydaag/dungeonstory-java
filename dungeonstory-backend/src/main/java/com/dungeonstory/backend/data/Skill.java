package com.dungeonstory.backend.data;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

@Entity
@Table(name = "Skill")
public class Skill extends AbstractTimestampEntity {

    private static final long serialVersionUID = -2967904843715939261L;

    @NotNull
    @Size(min = 1)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "shortDescription")
    private String shortDescription;
    
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
    @JoinColumn(name = "keyAbilityId", nullable = false)
    private Ability keyAbility;

    @ManyToMany(mappedBy = "skillProficiencies")
    private List<Character> characters;

    @OneToMany(mappedBy = "skill")
    private List<ClassSkill> classes;

    public Skill() {
        super();
    }

    public Skill(String name, Ability ability) {
        this();
        this.name = name;
        this.keyAbility = ability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ability getKeyAbility() {
        return keyAbility;
    }

    public void setKeyAbility(Ability keyAbility) {
        this.keyAbility = keyAbility;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public List<ClassSkill> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassSkill> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return getName();
    }

}
