package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@SuppressWarnings("serial")
@Entity
public class ArmorType extends AbstractTimestampEntity implements Serializable {
	public enum ProfiencyType {
		LIGHT, MEDIUM, HEAVY, SHIELD
	}

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Column(name = "profiencyType", nullable = false)
	@Enumerated(EnumType.STRING)
	private ProfiencyType profiencyType;

	@Column(name = "maxDexBonus", nullable = false)
	private int maxDexBonus;

	@Column(name = "baseArmorClass", nullable = false)
	private int baseArmorClass;

	@Column(name = "baseArmorCheckPenalty", nullable = false)
	private int baseArmorCheckPenalty = 0;

	@Column(name = "baseArcaneSpellFailure", nullable = false)
	private int baseArcaneSpellFailure = 0;

	@Column(name = "baseWeight", nullable = false)
	private int baseWeight;

	@Column(name = "speed", nullable = false)
	private int speed;

	public ArmorType() {
		// TODO Auto-generated constructor stub
	}

	public ArmorType(String name, String description,
			ProfiencyType profiencyType, int maxDexBonus, int baseArmorClass,
			int baseArmorCheckPenalty, int baseArcaneSpellFailure,
			int baseWeight, int speed) {
		super();
		this.name = name;
		this.description = description;
		this.profiencyType = profiencyType;
		this.maxDexBonus = maxDexBonus;
		this.baseArmorClass = baseArmorClass;
		this.baseArmorCheckPenalty = baseArmorCheckPenalty;
		this.baseArcaneSpellFailure = baseArcaneSpellFailure;
		this.baseWeight = baseWeight;
		this.speed = speed;
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

	public ProfiencyType getProfiencyType() {
		return profiencyType;
	}

	public void setProfiencyType(ProfiencyType profiencyType) {
		this.profiencyType = profiencyType;
	}

	public int getMaxDexBonus() {
		return maxDexBonus;
	}

	public void setMaxDexBonus(int maxDexBonus) {
		this.maxDexBonus = maxDexBonus;
	}

	public int getBaseArmorClass() {
		return baseArmorClass;
	}

	public void setBaseArmorClass(int baseArmorClass) {
		this.baseArmorClass = baseArmorClass;
	}

	public int getBaseArmorCheckPenalty() {
		return baseArmorCheckPenalty;
	}

	public void setBaseArmorCheckPenalty(int baseArmorCheckPenalty) {
		this.baseArmorCheckPenalty = baseArmorCheckPenalty;
	}

	public int getBaseArcaneSpellFailure() {
		return baseArcaneSpellFailure;
	}

	public void setBaseArcaneSpellFailure(int baseArcaneSpellFailure) {
		this.baseArcaneSpellFailure = baseArcaneSpellFailure;
	}

	public int getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(int baseWeight) {
		this.baseWeight = baseWeight;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
