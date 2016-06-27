package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@SuppressWarnings("serial")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@Table(name = "Equipment")
public class Equipment extends AbstractTimestampEntity implements Serializable {
	public enum EquipmentType {
		ARMOR, WEAPON, RING, AMULET, BRACER, BOOT, BELT, UTIL
	}

	@Column(name = "name", unique = true, nullable = false)
	private String name;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;

	@Min(value = 0)
	@Column(name = "weight", nullable = false)
	private double weight;

	@Column(name = "isMagical", nullable = false)
	private boolean isMagical;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", nullable = false)
	private EquipmentType type;

	@Column(name = "isPurchasable", nullable = false)
	private boolean isPurchasable;

	@Column(name = "isSellable", nullable = false)
	private boolean isSellable;

	public Equipment() {

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

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public boolean isMagical() {
		return isMagical;
	}

	public void setMagical(boolean isMagical) {
		this.isMagical = isMagical;
	}

	public EquipmentType getType() {
		return type;
	}

	public void setType(EquipmentType type) {
		this.type = type;
	}

	public boolean isPurchasable() {
		return isPurchasable;
	}

	public void setPurchasable(boolean isPurchasable) {
		this.isPurchasable = isPurchasable;
	}

	public boolean isSellable() {
		return isSellable;
	}

	public void setSellable(boolean isSellable) {
		this.isSellable = isSellable;
	}
}
