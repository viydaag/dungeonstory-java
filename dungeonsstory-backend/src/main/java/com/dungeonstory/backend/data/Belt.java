package com.dungeonstory.backend.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("BELT")
@Table(name = "Equipment")
public class Belt extends Equipment {

	private static final long serialVersionUID = 5202636703642244811L;

	public Belt() {
		super();
	}

}
