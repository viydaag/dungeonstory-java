package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "DamageType")
public class DamageType extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = -8021182556188719646L;

    private static Long idDamageType = 1L;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    public DamageType() {
        super();
        setId(idDamageType++);
    }

    public DamageType(String name) {
        this();
        this.name = name;
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

    @Override
    public String toString() {
        return getName();
    }

}
