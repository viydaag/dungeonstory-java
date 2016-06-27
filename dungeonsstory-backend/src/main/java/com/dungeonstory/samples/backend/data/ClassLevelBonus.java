package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

@SuppressWarnings("serial")
@Entity
@IdClass(ClassLevelBonusId.class)
public class ClassLevelBonus extends AbstractTimestampEntity implements Serializable {
	
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

	@Min(value = 0)
	@Column(name = "baseAttackBonus")
	private int baseAttackBonus;

	@Min(value = 0)
	@Column(name = "fortSave")
	private int fortSave;

	@Min(value = 0)
	@Column(name = "refSave")
	private int refSave;

	@Min(value = 0)
	@Column(name = "willSave")
	private int willSave;

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

}
