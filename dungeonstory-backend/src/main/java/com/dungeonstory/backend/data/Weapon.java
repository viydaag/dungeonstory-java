package com.dungeonstory.backend.data;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.eclipse.persistence.annotations.JoinFetch;
import org.eclipse.persistence.annotations.JoinFetchType;

@Entity
@DiscriminatorValue("WEAPON")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
@Table(name = "Weapon")
public class Weapon extends Equipment {

    private static final long serialVersionUID = 4509403800242686338L;

    @Pattern(regexp = "\\d+d\\d+([\\+\\-]\\d+)*")
    @Column(name = "oneHandDamage")
    private String oneHandDamage;
    
    @Pattern(regexp = "\\d+d\\d+([\\+\\-]\\d+)*")
    @Column(name = "twoHandDamage")
    private String twoHandDamage;

    @Pattern(regexp = "\\d+d\\d+([\\+\\-]\\d+)*")
    @Column(name = "additionalDamage")
    private String additionalDamage;

    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
    @JoinColumn(name = "additionalDamageTypeId")
    private DamageType additionalDamageType;

    @Column(name = "magicalBonus")
    private Integer magicalBonus;

    @NotNull
    @ManyToOne
    @JoinFetch(JoinFetchType.INNER)
    @JoinColumn(name = "weaponTypeId")
    private WeaponType weaponType;

    public Weapon() {
        super();
    }

	public String getOneHandDamage() {
		return oneHandDamage;
	}

	public void setOneHandDamage(String oneHandDamage) {
		this.oneHandDamage = oneHandDamage;
	}

	public String getTwoHandDamage() {
		return twoHandDamage;
	}

	public void setTwoHandDamage(String twoHandDamage) {
		this.twoHandDamage = twoHandDamage;
	}

	public String getAdditionalDamage() {
		return additionalDamage;
	}

	public void setAdditionalDamage(String additionalDamage) {
		this.additionalDamage = additionalDamage;
	}

	public DamageType getAdditionalDamageType() {
		return additionalDamageType;
	}

	public void setAdditionalDamageType(DamageType additionalDamageType) {
		this.additionalDamageType = additionalDamageType;
	}

	public Integer getMagicalBonus() {
		return magicalBonus;
	}

	public void setMagicalBonus(Integer magicalBonus) {
		this.magicalBonus = magicalBonus;
	}

	public WeaponType getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(WeaponType weaponType) {
		this.weaponType = weaponType;
	}

}
