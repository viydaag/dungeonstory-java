package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
@IdClass(CharacterSkillId.class)
public class CharacterSkill implements Serializable {
	@Id
	@Column(name = "characterId")
	private Long characterId;

	@Id
	@Column(name = "skillId")
	private Long skillId;

	@Column(name = "rank")
	private int rank;

	@ManyToOne
	@JoinColumn(name = "characterId", updatable = false, insertable = false)
	private Character character;

	@ManyToOne
	@JoinColumn(name = "skillId", updatable = false, insertable = false)
	private Skill skill;

	public CharacterSkill() {
		// TODO Auto-generated constructor stub
	}

	public Long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Long characterId) {
		this.characterId = characterId;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

}
