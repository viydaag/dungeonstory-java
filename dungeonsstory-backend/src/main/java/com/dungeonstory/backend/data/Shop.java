package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Shop")
public class Shop extends AbstractTimestampEntity implements Serializable {

    private static final long serialVersionUID = 4969359354645768702L;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public Shop() {

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
