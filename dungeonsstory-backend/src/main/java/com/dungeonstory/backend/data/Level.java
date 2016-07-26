package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Level")
public class Level implements com.dungeonstory.backend.repository.Entity, Serializable {

    private static final long serialVersionUID = 4749488433122909200L;

    @Id
    @NotNull
    @Column(name = "id")
    private Long id; // id is the level number

    @Version
    @Column(name = "version")
    private int version;

    @NotNull
    @Min(value = 0)
    @Column(name = "maxExperience")
    private long maxExperience;

    @NotNull
    @Min(value = 0)
    @Column(name = "proficiencyBonus")
    private int proficiencyBonus;

    @OneToMany(mappedBy = "level")
    private List<ClassLevelBonus> classBonuses;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", nullable = false)
    private Date updated;

    public Level() {
        super();
    }

    public Level(long maxExperience, int proficiencyBonus) {
        this();
        this.maxExperience = maxExperience;
        this.proficiencyBonus = proficiencyBonus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getMaxExperience() {
        return maxExperience;
    }

    public void setMaxExperience(long maxExperience) {
        this.maxExperience = maxExperience;
    }

    public int getProficiencyBonus() {
        return proficiencyBonus;
    }

    public void setProficiencyBonus(int proficiencyBonus) {
        this.proficiencyBonus = proficiencyBonus;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @PrePersist
    protected void onCreate() {
        updated = created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = new Date();
    }

    @Override
    public String toString() {
        return getId().toString();
    }

}
