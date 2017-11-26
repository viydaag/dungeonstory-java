package com.dungeonstory.backend.data;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.eclipse.persistence.annotations.BatchFetch;
import org.eclipse.persistence.annotations.BatchFetchType;
import org.eclipse.persistence.annotations.Customizer;

import com.dungeonstory.backend.data.util.OrderCustomizer;

@Entity
@Table(name = "Level")
@Customizer(OrderCustomizer.class)
public class Level implements com.dungeonstory.backend.repository.Entity {

    private static final long serialVersionUID = 4749488433122909200L;

    @Id
    @NotNull
    @Column(name = "id")
    private Long id; // id is the level number

    @NotNull
    @Min(value = 0)
    @Digits(integer = 12, fraction = 0)
    @Column(name = "maxExperience", nullable = false)
    private long maxExperience;

    @NotNull
    @Min(value = 0)
    @Digits(integer = 1, fraction = 0)
    @Column(name = "proficiencyBonus", nullable = false)
    private int proficiencyBonus;

    @OneToMany(mappedBy = "level")
    @BatchFetch(value = BatchFetchType.JOIN)
    private List<ClassLevelBonus> classBonuses;
    
    @Version
    @Column(name = "version")
    private int version;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

    public Level() {
        super();
    }

    public Level(long id, long maxExperience, int proficiencyBonus) {
        this();
        this.id = id;
        this.maxExperience = maxExperience;
        this.proficiencyBonus = proficiencyBonus;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    public List<ClassLevelBonus> getClassBonuses() {
        return classBonuses;
    }

    public void setClassBonuses(List<ClassLevelBonus> classBonuses) {
        this.classBonuses = classBonuses;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @PrePersist
    protected void onCreate() {
        updated = created = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return getId().toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Level other = (Level) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
