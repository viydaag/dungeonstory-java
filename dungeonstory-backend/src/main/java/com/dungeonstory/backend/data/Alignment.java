package com.dungeonstory.backend.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Alignment")
@NamedQuery(name = Alignment.FIND_ALL_PLAYABLE, query = "SELECT a FROM Alignment a WHERE a.playable = 1")
public class Alignment extends AbstractTimestampEntity {

    private static final long serialVersionUID = -518798894253295092L;

    public static final String FIND_ALL_PLAYABLE = "findAllPlayable";

    @NotNull
    @Size(min = 1)
    @Column(name = "name", unique = true)
    private String name;
    
    @Size(max = 2)
    @Column(name = "abbreviation", length = 2, unique = true)
    private String abbreviation;

    @Column(name = "shortDescription")
    private String shortDescription;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "playable")
    private boolean playable;

    public Alignment() {
        super();
    }

    public Alignment(String name, String shortDescription, String description, boolean playable) {
        this();
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.playable = playable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
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

    public boolean getPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    @Override
    public String toString() {
        return getName();
    }

}
