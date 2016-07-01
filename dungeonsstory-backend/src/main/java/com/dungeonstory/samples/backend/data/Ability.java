package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Ability")
public class Ability extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = 851333358232556465L;

	private static Long idAbility = 1L;
	
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "abbreviation", length = 3)
	private String abbreviation;

	public Ability() {
		super();
		setId(idAbility++);
	}

	public Ability(String name, String abbreviation, String description) {
		this();
		this.name = name;
		this.abbreviation = abbreviation;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
