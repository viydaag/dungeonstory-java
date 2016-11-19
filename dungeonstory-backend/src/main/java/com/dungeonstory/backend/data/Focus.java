package com.dungeonstory.backend.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("FOCUS")
@Table(name = "Equipment")
public class Focus extends Equipment {

    private static final long serialVersionUID = -5733155443030053352L;

}
