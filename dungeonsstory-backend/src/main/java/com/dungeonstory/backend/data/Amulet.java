package com.dungeonstory.backend.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("AMULET")
@Table(name = "Equipment")
public class Amulet extends Equipment {

	private static final long serialVersionUID = 2238056304826928161L;

	public Amulet() {
		super();
	}

}
