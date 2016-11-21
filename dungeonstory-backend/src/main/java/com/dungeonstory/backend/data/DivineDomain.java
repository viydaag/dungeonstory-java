package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.PrivateOwned;

@Entity
@Table(name = "DivineDomain")
@NamedQuery(name = DivineDomain.FIND_ALL_BY_DEITY, query = "SELECT dd FROM DivineDomain dd JOIN dd.deities d WHERE d.id = :deityId")
public class DivineDomain extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = -3668589967173883247L;

    public static final String FIND_ALL_BY_DEITY = "findAllByDeity";

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @OneToMany(mappedBy = "domain", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @PrivateOwned
    private List<DivineDomainSpell> spells;
    
    @ManyToMany(mappedBy = "domains")
    private List<Deity> deities;

    public DivineDomain() {
        super();
        spells = new ArrayList<DivineDomainSpell>();
        deities = new ArrayList<Deity>();
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
    
    public List<Deity> getDeities() {
        return deities;
    }

    public void setDeities(List<Deity> deities) {
        this.deities = deities;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public DivineDomain clone() {
        DivineDomain domain;
        try {
            domain = (DivineDomain) super.clone();
            domain.setSpells(new ArrayList<>(getSpells()));
            domain.setDeities(new ArrayList<>(getDeities()));
            return domain;
        } catch (CloneNotSupportedException e) {
            return null;
        }

    }

}
