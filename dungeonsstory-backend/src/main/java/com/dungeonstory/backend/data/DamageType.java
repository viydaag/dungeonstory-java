package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DamageType")
public class DamageType extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = -8021182556188719646L;
	
	@Column(name = "name", unique = true)
	private String name;

	public DamageType() {
		super();
	}

	public DamageType(String name) {
		this();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
