package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Alignment")
public class Alignment extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = -518798894253295092L;
	
	private static Long idAlign = 1L;

	@NotNull
	@Column(name = "name", unique = true)
	private String name;

	@Column(name = "shortDescription")
	private String shortDescription;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	public Alignment() {
		super();
		setId(idAlign++);
	}

	public Alignment(String name, String shortDescription, String description) {
		this();
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
