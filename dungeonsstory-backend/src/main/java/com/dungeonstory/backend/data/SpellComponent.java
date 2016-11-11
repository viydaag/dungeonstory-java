package com.dungeonstory.backend.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("COMPONENT")
@Table(name = "Equipment")
public class SpellComponent extends Equipment {

    private static final long serialVersionUID = 690607970052993238L;

    public SpellComponent() {
        super();
    }
    
    @Override
    public String toString() {
        return getName();
    }

}
