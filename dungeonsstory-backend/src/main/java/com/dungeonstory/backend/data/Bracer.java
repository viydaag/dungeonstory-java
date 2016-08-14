package com.dungeonstory.backend.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("BRACER")
@Table(name = "Equipment")
public class Bracer extends Equipment {

	private static final long serialVersionUID = -5663851714905294026L;
	
	public Bracer() {
		super();
	}

}
