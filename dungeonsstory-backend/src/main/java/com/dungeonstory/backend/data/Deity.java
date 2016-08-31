package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Deity")
public class Deity extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = -4390870225200515952L;
    
    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "alignmentId")
    private Alignment alignment;
    
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "DeityDomain", joinColumns = {
        @JoinColumn(name = "deityId", referencedColumnName = "id") }, 
            inverseJoinColumns = { @JoinColumn(name = "domainId", referencedColumnName = "id") })
    private Set<DivineDomain> domains;
    
    @Column(name = "symbol")
    private String symbol;

    public Deity() {
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

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public Set<DivineDomain> getDomains() {
        return domains;
    }

    public void setDomains(Set<DivineDomain> domains) {
        this.domains = domains;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
