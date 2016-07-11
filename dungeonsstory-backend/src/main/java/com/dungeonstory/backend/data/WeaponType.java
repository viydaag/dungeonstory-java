package com.dungeonstory.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "WeaponType")
public class WeaponType extends AbstractTimestampEntity implements Serializable {

	private static final long serialVersionUID = -5780288141494436969L;
	
	public enum ProficiencyType {
		SIMPLE, MARTIAL
	}
	
	public enum HandleType {
		LIGHT, ONE_HANDED, TWO_HANDED
	}
	
	public enum UsageType {
		MELEE, RANGE
	}
	
	public enum RangeType {
		PROJECTILE, THROWN
	}
	
	@NotNull
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@NotNull
	@Column(name = "proficiencyType", nullable = false)
	@Enumerated(EnumType.STRING)
	private ProficiencyType proficiencyType;
	
	@NotNull
	@Column(name = "handleType", nullable = false)
	@Enumerated(EnumType.STRING)
	private HandleType handleType;
	
	@NotNull
	@Column(name = "usageType", nullable = false)
	@Enumerated(EnumType.STRING)
	private UsageType usageType;
	
	@Column(name = "rangeType", nullable = false)
	@Enumerated(EnumType.STRING)
	private RangeType rangeType;
	
	@NotNull
	@Pattern(regexp = "\\d+d\\d+")
	@Column(name = "baseDamage")
	private String baseDamage;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "damageTypeId")
	private DamageType damageType;
	
	@Column(name = "isReach")
	private boolean isReach;
	
	@NotNull
	@Min(value = 0)
	@Column(name = "baseWeight")
	private int baseWeight;

	public WeaponType() {
		super();
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

	public ProficiencyType getProficiencyType() {
		return proficiencyType;
	}

	public void setProficiencyType(ProficiencyType proficiencyType) {
		this.proficiencyType = proficiencyType;
	}

	public HandleType getHandleType() {
		return handleType;
	}

	public void setHandleType(HandleType handleType) {
		this.handleType = handleType;
	}

	public UsageType getUsageType() {
		return usageType;
	}

	public void setUsageType(UsageType usageType) {
		this.usageType = usageType;
	}

	public RangeType getRangeType() {
		return rangeType;
	}

	public void setRangeType(RangeType rangeType) {
		this.rangeType = rangeType;
	}

	public String getBaseDamage() {
		return baseDamage;
	}

	public void setBaseDamage(String baseDamage) {
		this.baseDamage = baseDamage;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}

	public boolean isReach() {
		return isReach;
	}

	public void setReach(boolean isReach) {
		this.isReach = isReach;
	}

	public int getBaseWeight() {
		return baseWeight;
	}

	public void setBaseWeight(int baseWeight) {
		this.baseWeight = baseWeight;
	}

}
