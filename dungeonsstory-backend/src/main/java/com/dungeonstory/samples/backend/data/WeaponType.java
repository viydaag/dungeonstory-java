package com.dungeonstory.samples.backend.data;

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
	private boolean baseWeight;

	public WeaponType() {
		super();
	}

}
