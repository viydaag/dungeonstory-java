package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Background")
public class Background extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = -8141940835860939919L;
    
    public enum LanguageChoice {
        NONE,
        ONE,
        TWO
    }

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @Column(name = "traits", columnDefinition = "TEXT")
    private String traits;
    
    @Column(name = "ideals", columnDefinition = "TEXT")
    private String ideals;
    
    @Column(name = "purposes", columnDefinition = "TEXT")
    private String purposes;
    
    @Column(name = "flaws", columnDefinition = "TEXT")
    private String flaws;
    
    @ManyToMany
    @JoinTable(name = "BackgroundSkillProficiencies", joinColumns = {
        @JoinColumn(name = "backgroundId", referencedColumnName = "id") }, 
            inverseJoinColumns = { @JoinColumn(name = "skillId", referencedColumnName = "id") })
    private Set<Skill> skillProficiencies;
    
    @ManyToMany
    @JoinTable(name = "BackgroundToolProficiencies", joinColumns = {
        @JoinColumn(name = "backgroundId", referencedColumnName = "id") }, 
            inverseJoinColumns = { @JoinColumn(name = "toolId", referencedColumnName = "id") })
    private Set<Tool> toolProficiencies;
    
    @NotNull
    @Column(name = "additionalLanguage", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private LanguageChoice additionalLanguage = LanguageChoice.NONE;
    
    public Background() {
        super();
        skillProficiencies = new HashSet<Skill>();
        toolProficiencies = new HashSet<Tool>();
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

    public String getTraits() {
        return traits;
    }

    public void setTraits(String traits) {
        this.traits = traits;
    }

    public String getIdeals() {
        return ideals;
    }

    public void setIdeals(String ideals) {
        this.ideals = ideals;
    }

    public String getPurposes() {
        return purposes;
    }

    public void setPurposes(String purposes) {
        this.purposes = purposes;
    }

    public String getFlaws() {
        return flaws;
    }

    public void setFlaws(String flaws) {
        this.flaws = flaws;
    }

    public Set<Skill> getSkillProficiencies() {
        return skillProficiencies;
    }

    public void setSkillProficiencies(Set<Skill> skillProficiencies) {
        this.skillProficiencies = skillProficiencies;
    }

    public Set<Tool> getToolProficiencies() {
        return toolProficiencies;
    }

    public void setToolProficiencies(Set<Tool> toolProficiencies) {
        this.toolProficiencies = toolProficiencies;
    }

    public LanguageChoice getAdditionalLanguage() {
        return additionalLanguage;
    }

    public void setAdditionalLanguage(LanguageChoice additionalLanguage) {
        this.additionalLanguage = additionalLanguage;
    }

    @Override
    public String toString() {
        return getName();
    }

}
