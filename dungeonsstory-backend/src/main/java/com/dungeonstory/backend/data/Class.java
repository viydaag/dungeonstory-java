package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "Class")
public class Class extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = 4948845539537092288L;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "shortDescription")
	private String shortDescription;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Min(value = 0)
	@Column(name = "lifePointPerLevel", nullable = false)
	private int lifePointPerLevel;

	@OneToMany(mappedBy = "classe")
	private List<ClassSkill> skills;

	@OneToMany(mappedBy = "classe")
	private List<ClassLevelBonus> levelBonuses;

	public Class() {
		// TODO Auto-generated constructor stub
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

	public int getLifePointPerLevel() {
		return lifePointPerLevel;
	}

	public void setLifePointPerLevel(int lifePointPerLevel) {
		this.lifePointPerLevel = lifePointPerLevel;
	}

}
