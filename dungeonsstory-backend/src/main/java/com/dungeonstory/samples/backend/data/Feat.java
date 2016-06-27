package com.dungeonstory.samples.backend.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;

@SuppressWarnings("serial")
@Entity
public class Feat extends AbstractTimestampEntity implements Serializable {
	public enum FeatType {
		GENERAL, ITEM_CREATION, METAMAGIC
	}

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private FeatType type;

	@ManyToMany(mappedBy = "feats")
	private List<Character> characters;

	public Feat() {
		// TODO Auto-generated constructor stub
	}

	public Feat(String name, String description, FeatType type) {
		super();
		this.name = name;
		this.description = description;
		this.type = type;
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

	public FeatType getType() {
		return type;
	}

	public void setType(FeatType type) {
		this.type = type;
	}

}
