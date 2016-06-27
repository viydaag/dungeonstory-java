package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Ability")
public class DamageType extends AbstractTimestampEntity implements Serializable {

	@Column(name = "name", unique = true)
	private String name;

	public DamageType() {

	}

	public DamageType(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
