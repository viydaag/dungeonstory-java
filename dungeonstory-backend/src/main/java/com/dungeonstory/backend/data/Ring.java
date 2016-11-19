package com.dungeonstory.backend.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("RING")
@Table(name = "Equipment")
public class Ring extends Equipment {

	private static final long serialVersionUID = -647714620033815601L;

	public Ring() {
		super();
	}

}
