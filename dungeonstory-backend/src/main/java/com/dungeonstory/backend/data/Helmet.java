package com.dungeonstory.backend.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("HELMET")
@Table(name = "Equipment")
public class Helmet extends Equipment {

    private static final long serialVersionUID = 1579740342222117735L;
    
}
