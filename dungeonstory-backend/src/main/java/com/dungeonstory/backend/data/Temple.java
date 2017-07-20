package com.dungeonstory.backend.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Temple")
public class Temple extends AbstractTimestampEntity {

    private static final long serialVersionUID = -8827164402574931582L;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private City city;
    
    @ManyToOne
    @JoinColumn(name = "deityId")
    private Deity deity;

    public Temple() {
        super();
    }
    
    public Temple(String name, Deity deity, City city) {
        super();
        setName(name);
        setDeity(deity);
        setCity(city);
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Deity getDeity() {
        return deity;
    }

    public void setDeity(Deity deity) {
        this.deity = deity;
    }

}
