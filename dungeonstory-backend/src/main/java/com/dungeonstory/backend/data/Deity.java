package com.dungeonstory.backend.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;

import com.dungeonstory.backend.data.enums.Alignment;

@Entity
@Table(name = "Deity")
@NamedQuery(name = Deity.FIND_ALL_BY_DOMAIN, query = "SELECT deity FROM Deity deity JOIN deity.domains domain WHERE domain.id = :domainId")
public class Deity extends AbstractTimestampEntity {

    public static final String FIND_ALL_BY_DOMAIN = "findAllByDomain";

    private static final long serialVersionUID = -4390870225200515952L;

    @NotNull
    @Size(min = 1)
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "shortDescription")
    private String shortDescription;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "alignment", nullable = false)
    private Alignment alignment;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @BatchFetch(value = BatchFetchType.JOIN)
    @JoinTable(name = "DeityDomain", joinColumns = {
            @JoinColumn(name = "deityId", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "domainId", referencedColumnName = "id") })
    private Set<ClassSpecialization> domains;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "image")
    private byte[] image;

    public Deity() {
        super();
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

    public Alignment getAlignment() {
        return alignment;
    }

    public void setAlignment(Alignment alignment) {
        this.alignment = alignment;
    }

    public Set<ClassSpecialization> getDomains() {
        return domains;
    }

    public void setDomains(Set<ClassSpecialization> domains) {
        this.domains = domains;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public Deity clone() {
        Deity deity;
        try {
            deity = (Deity) super.clone();
            deity.setDomains(new HashSet<>(getDomains()));
            return deity;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return getName() + " - " + getAlignment().getAbbreviation();
    }

}
