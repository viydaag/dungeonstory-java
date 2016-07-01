package com.dungeonstory.samples.backend.data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@DiscriminatorValue("WEAPON")
@Table(name = "Weapon")
public class Weapon extends Equipment {
	
	private static final long serialVersionUID = 4509403800242686338L;

	@NotNull
	@Pattern(regexp = "\\d+d\\d+")
	@Column(name = "damage")
	private String damage;
	
	@NotNull
	@Pattern(regexp = "\\d+d\\d+")
	@Column(name = "additionalDamage")
	private String additionalDamage;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "additionalDamageTypeId")
	private DamageType additionalDamageType;
	
	@Column(name = "magicalBonus")
	private int magicalBonus;
	
	@ManyToOne
	@JoinColumn(name = "weaponTypeId")
	private WeaponType weaponType;

	public Weapon() {
		super();
	}

}
