package com.dungeonstory.backend.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Class")
public class DSClass extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = 4948845539537092288L;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "shortDescription")
	private String shortDescription;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Min(value = 0)
	@Column(name = "lifePointPerLevel", nullable = false)
	private int lifePointPerLevel;

	@ManyToMany
    @JoinTable(
        name="ClassSkill",
        joinColumns={@JoinColumn(name="classId", referencedColumnName="id")},
        inverseJoinColumns={@JoinColumn(name="skillId", referencedColumnName="id")})
	private Set<Skill> baseSkills;

	@OneToMany(mappedBy = "classe")
	private List<ClassLevelBonus> levelBonuses;

	public DSClass() {
	    baseSkills = new HashSet<Skill>();
	    levelBonuses = new ArrayList<ClassLevelBonus>();
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

    public Set<Skill> getBaseSkills() {
        return baseSkills;
    }

    public void setBaseSkills(Set<Skill> baseSkills) {
        this.baseSkills = baseSkills;
    }

    public List<ClassLevelBonus> getLevelBonuses() {
        return levelBonuses;
    }

    public void setLevelBonuses(List<ClassLevelBonus> levelBonuses) {
        this.levelBonuses = levelBonuses;
    }
    
    @Override
    public String toString() {
        return getName();
    }

}
