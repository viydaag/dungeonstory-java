package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@IdClass(ClassLevelBonusId.class)
@Table(name = "ClassLevelBonus")
public class ClassLevelBonus implements Serializable {
	
	private static final long serialVersionUID = 2087402735649166519L;

	@Id
	@Column(name = "classId")
	private Long classId;

	@Id
	@Column(name = "levelId")
	private Long levelId;

	@ManyToOne
	@JoinColumn(name = "classId", updatable = false, insertable = false)
	private Class classe;

	@ManyToOne
	@JoinColumn(name = "levelId", updatable = false, insertable = false)
	private Level level;
	
	@Column(name = "abilityScoreImprovement")
	private boolean hasAbilityScoreImprovement;

//	@Min(value = 0)
//	@Column(name = "baseAttackBonus")
//	private int baseAttackBonus;
//
//	@Min(value = 0)
//	@Column(name = "fortSave")
//	private int fortSave;
//
//	@Min(value = 0)
//	@Column(name = "refSave")
//	private int refSave;
//
//	@Min(value = 0)
//	@Column(name = "willSave")
//	private int willSave;

	@Min(value = 0)
	@Column(name = "armorClassBonus")
	private int armorClassBonus;

	@Min(value = 0)
	@Column(name = "spellBonus")
	private int spellBonus;

	@Min(value = 0)
	@Column(name = "spellPerDay0")
	private int spellPerDay0;

	@Min(value = 0)
	@Column(name = "spellPerDay1")
	private int spellPerDay1;

	@Min(value = 0)
	@Column(name = "spellPerDay2")
	private int spellPerDay2;

	@Min(value = 0)
	@Column(name = "spellPerDay3")
	private int spellPerDay3;

	@Min(value = 0)
	@Column(name = "spellPerDay4")
	private int spellPerDay4;

	@Min(value = 0)
	@Column(name = "spellPerDay5")
	private int spellPerDay5;

	@Min(value = 0)
	@Column(name = "spellPerDay6")
	private int spellPerDay6;

	@Min(value = 0)
	@Column(name = "spellPerDay7")
	private int spellPerDay7;

	@Min(value = 0)
	@Column(name = "spellPerDay8")
	private int spellPerDay8;

	@Min(value = 0)
	@Column(name = "spellPerDay9")
	private int spellPerDay9;

	public ClassLevelBonus() {
		// TODO Auto-generated constructor stub
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

	public Long getLevelId() {
		return levelId;
	}

	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}

	public Class getClasse() {
		return classe;
	}

	public void setClasse(Class classe) {
		this.classe = classe;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public boolean isHasAbilityScoreImprovement() {
		return hasAbilityScoreImprovement;
	}

	public void setHasAbilityScoreImprovement(boolean hasAbilityScoreImprovement) {
		this.hasAbilityScoreImprovement = hasAbilityScoreImprovement;
	}

	public int getArmorClassBonus() {
		return armorClassBonus;
	}

	public void setArmorClassBonus(int armorClassBonus) {
		this.armorClassBonus = armorClassBonus;
	}

	public int getSpellBonus() {
		return spellBonus;
	}

	public void setSpellBonus(int spellBonus) {
		this.spellBonus = spellBonus;
	}

	public int getSpellPerDay0() {
		return spellPerDay0;
	}

	public void setSpellPerDay0(int spellPerDay0) {
		this.spellPerDay0 = spellPerDay0;
	}

	public int getSpellPerDay1() {
		return spellPerDay1;
	}

	public void setSpellPerDay1(int spellPerDay1) {
		this.spellPerDay1 = spellPerDay1;
	}

	public int getSpellPerDay2() {
		return spellPerDay2;
	}

	public void setSpellPerDay2(int spellPerDay2) {
		this.spellPerDay2 = spellPerDay2;
	}

	public int getSpellPerDay3() {
		return spellPerDay3;
	}

	public void setSpellPerDay3(int spellPerDay3) {
		this.spellPerDay3 = spellPerDay3;
	}

	public int getSpellPerDay4() {
		return spellPerDay4;
	}

	public void setSpellPerDay4(int spellPerDay4) {
		this.spellPerDay4 = spellPerDay4;
	}

	public int getSpellPerDay5() {
		return spellPerDay5;
	}

	public void setSpellPerDay5(int spellPerDay5) {
		this.spellPerDay5 = spellPerDay5;
	}

	public int getSpellPerDay6() {
		return spellPerDay6;
	}

	public void setSpellPerDay6(int spellPerDay6) {
		this.spellPerDay6 = spellPerDay6;
	}

	public int getSpellPerDay7() {
		return spellPerDay7;
	}

	public void setSpellPerDay7(int spellPerDay7) {
		this.spellPerDay7 = spellPerDay7;
	}

	public int getSpellPerDay8() {
		return spellPerDay8;
	}

	public void setSpellPerDay8(int spellPerDay8) {
		this.spellPerDay8 = spellPerDay8;
	}

	public int getSpellPerDay9() {
		return spellPerDay9;
	}

	public void setSpellPerDay9(int spellPerDay9) {
		this.spellPerDay9 = spellPerDay9;
	}

}
