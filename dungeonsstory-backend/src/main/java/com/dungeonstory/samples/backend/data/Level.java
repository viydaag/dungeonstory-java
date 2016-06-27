package com.dungeonstory.samples.backend.data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.Min;

@SuppressWarnings("serial")
@Entity
public class Level implements Serializable {
	
	@Id
	@Column(name = "id", unique = true)
	private int id;		//id is the level number
	
	@Version
	private int version;

	@Min(value = 0)
	@Column(name = "maxExperience")
	private long maxExperience;

	@Min(value = 0)
	@Column(name = "bonusFeat")
	private boolean hasBonusFeat;

	@Min(value = 0)
	@Column(name = "bonusAbilityScore")
	private boolean hasBonusAbilityScore;

	@Min(value = 0)
	@Column(name = "maxClassSkillRanks")
	private int maxClassSkillRanks;

	@Min(value = 0)
	@Column(name = "maxCrossClassSkillRanks")
	private int maxCrossClassSkillRanks;

	@OneToMany(mappedBy = "level")
	private List<ClassLevelBonus> classBonuses;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false)
	private Date updated;

	public Level() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getMaxExperience() {
		return maxExperience;
	}

	public void setMaxExperience(long maxExperience) {
		this.maxExperience = maxExperience;
	}

	public boolean isHasBonusFeat() {
		return hasBonusFeat;
	}

	public void setHasBonusFeat(boolean hasBonusFeat) {
		this.hasBonusFeat = hasBonusFeat;
	}

	public boolean isHasBonusAbilityScore() {
		return hasBonusAbilityScore;
	}

	public void setHasBonusAbilityScore(boolean hasBonusAbilityScore) {
		this.hasBonusAbilityScore = hasBonusAbilityScore;
	}

	public int getMaxClassSkillRanks() {
		return maxClassSkillRanks;
	}

	public void setMaxClassSkillRanks(int maxClassSkillRanks) {
		this.maxClassSkillRanks = maxClassSkillRanks;
	}

	public int getMaxCrossClassSkillRanks() {
		return maxCrossClassSkillRanks;
	}

	public void setMaxCrossClassSkillRanks(int maxCrossClassSkillRanks) {
		this.maxCrossClassSkillRanks = maxCrossClassSkillRanks;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@PrePersist
	protected void onCreate() {
		updated = created = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		updated = new Date();
	}

}
