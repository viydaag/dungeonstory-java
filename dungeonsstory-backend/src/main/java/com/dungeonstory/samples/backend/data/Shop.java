package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

import javax.persistence.Column;

@SuppressWarnings("serial")
public class Shop extends AbstractTimestampEntity implements Serializable {
	
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	public Shop() {

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
