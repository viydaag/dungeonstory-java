package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Race")
public class Race extends AbstractTimestampEntity implements Serializable {
	
	private static final long serialVersionUID = -8654082699083199159L;

	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "shortDescription")
	private String shortDescription;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@NotNull
	@Min(value = 0)
	@Column(name = "strModifier")
	private int strModifier;

	@NotNull
	@Min(value = 0)
	@Column(name = "dexModifier")
	private int dexModifier;

	@NotNull
	@Min(value = 0)
	@Column(name = "conModifier")
	private int conModifier;

	@NotNull
	@Min(value = 0)
	@Column(name = "intModifier")
	private int intModifier;

	@NotNull
	@Min(value = 0)
	@Column(name = "wisModifier")
	private int wisModifier;

	@NotNull
	@Min(value = 0)
	@Column(name = "chaModifier")
	private int chaModifier;

	@NotNull
	@Min(value = 0)
	@Column(name = "minAge")
	private int minAge;

	@NotNull
	@Min(value = 0)
	@Column(name = "maxAge")
	private int maxAge;

	@NotNull
	@Min(value = 0)
	@Column(name = "averageHeight")
	private int averageHeight;

	@NotNull
	@Min(value = 0)
	@Column(name = "averageWeight")
	private int averageWeight;

	@NotNull
	@Pattern(regexp = "\\d+d\\d+")
	@Column(name = "ageModifier")
	private String ageModifier;

	@NotNull
	@Pattern(regexp = "\\d+d\\d+")
	@Column(name = "weightModifier")
	private String weightModifier;

	@NotNull
	@Pattern(regexp = "\\d+d\\d+")
	@Column(name = "heightModifier")
	private String heightModifier;

	@Deprecated
	@ManyToOne
	@JoinColumn(name = "favoredClassId")
	private Class favoredClass;

	public Race() {
		super();
	}

	public Race(String name) {
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

	public Integer getStrModifier() {
		return strModifier;
	}

	public void setStrModifier(Integer strModifier) {
		this.strModifier = strModifier.intValue();
	}

	public int getDexModifier() {
		return dexModifier;
	}

	public void setDexModifier(int dexModifier) {
		this.dexModifier = dexModifier;
	}

	public int getConModifier() {
		return conModifier;
	}

	public void setConModifier(int conModifier) {
		this.conModifier = conModifier;
	}

	public int getIntModifier() {
		return intModifier;
	}

	public void setIntModifier(int intModifier) {
		this.intModifier = intModifier;
	}

	public int getWisModifier() {
		return wisModifier;
	}

	public void setWisModifier(int wisModifier) {
		this.wisModifier = wisModifier;
	}

	public int getChaModifier() {
		return chaModifier;
	}

	public void setChaModifier(int chaModifier) {
		this.chaModifier = chaModifier;
	}

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public int getAverageHeight() {
		return averageHeight;
	}

	public void setAverageHeight(int averageHeight) {
		this.averageHeight = averageHeight;
	}

	public int getAverageWeight() {
		return averageWeight;
	}

	public void setAverageWeight(int averageWeight) {
		this.averageWeight = averageWeight;
	}

	public String getAgeModifier() {
		return ageModifier;
	}

	public void setAgeModifier(String ageModifier) {
		this.ageModifier = ageModifier;
	}

	public String getWeightModifier() {
		return weightModifier;
	}

	public void setWeightModifier(String weightModifier) {
		this.weightModifier = weightModifier;
	}

	public String getHeightModifier() {
		return heightModifier;
	}

	public void setHeightModifier(String heightModifier) {
		this.heightModifier = heightModifier;
	}

	public Class getFavoredClass() {
		return favoredClass;
	}

	public void setFavoredClass(Class favoredClass) {
		this.favoredClass = favoredClass;
	}

}
