package com.dungeonstory.backend.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("GEAR")
@Table(name = "Equipment")
public class Gear extends Equipment {

    private static final long serialVersionUID = 6462395449821298553L;

}
