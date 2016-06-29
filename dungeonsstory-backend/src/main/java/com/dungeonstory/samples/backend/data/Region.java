package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
@Entity
public class Region extends AbstractTimestampEntity implements Serializable {
	
	@Size(min = 3)
	@NotNull
	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "shortDescription")
	private String shortDescription;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	public Region() {

	}

	public Region(String name) {
		super();
		this.name = name;
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
