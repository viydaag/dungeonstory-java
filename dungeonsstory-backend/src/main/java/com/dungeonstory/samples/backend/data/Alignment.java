package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

@SuppressWarnings("serial")
@Entity
public class Alignment extends AbstractTimestampEntity implements Serializable {

	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "shortDescription")
	private String shortDescription;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	public Alignment() {

	}

	public Alignment(String name, String shortDescription, String description) {
		super();
		this.name = name;
		this.shortDescription = shortDescription;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
