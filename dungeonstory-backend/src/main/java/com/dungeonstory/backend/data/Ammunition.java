package com.dungeonstory.backend.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("AMMUNITION")
@Table(name = "Equipment")
public class Ammunition extends Equipment {

    private static final long serialVersionUID = 6610228676432208592L;

}
