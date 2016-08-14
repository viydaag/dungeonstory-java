package com.dungeonstory.backend.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("TOOL")
@Table(name = "Equipment")
public class Tool extends Equipment {

	private static final long serialVersionUID = 4291593041863782823L;

	public Tool() {
		super();
	}

}
