package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DivineDomain")
public class DivineDomain extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = -3668589967173883247L;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @OneToMany(mappedBy = "domain", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<DivineDomainSpell> spells;
    
    public DivineDomain() {
        super();
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

    public List<DivineDomainSpell> getSpells() {
        return spells;
    }

    public void setSpells(List<DivineDomainSpell> spells) {
        this.spells = spells;
    }

}
