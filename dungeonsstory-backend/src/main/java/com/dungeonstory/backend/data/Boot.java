package com.dungeonstory.backend.data;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("BOOT")
@Table(name = "Equipment")
public class Boot extends Equipment {

	private static final long serialVersionUID = 1669792485625029501L;

	public Boot() {
		super();
	}

}
