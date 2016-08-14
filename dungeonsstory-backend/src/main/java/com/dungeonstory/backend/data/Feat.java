package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Feat")
public class Feat extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 291837938711381342L;

    public enum FeatUsage {
        PASSIVE, ACTION, REACTION
    }

    @NotNull
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "featUsage", nullable = false)
    private FeatUsage usage;

    @ManyToMany(mappedBy = "feats")
    private List<Character> characters;

    public Feat() {
        super();
    }

    public Feat(String name, String description, FeatUsage type) {
        super();
        this.name = name;
        this.description = description;
        this.usage = type;
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

    public FeatUsage getUsage() {
        return usage;
    }

    public void setUsage(FeatUsage usage) {
        this.usage = usage;
    }

    @Override
    public String toString() {
        return getName();
    }

}
