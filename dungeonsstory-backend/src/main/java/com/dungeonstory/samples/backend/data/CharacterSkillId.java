package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CharacterSkillId implements Serializable {
	private Long characterId;

	private Long skillId;

	public CharacterSkillId() {
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

}
