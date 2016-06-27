package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CharacterClassId implements Serializable {
	private Long characterId;

	private Long classId;

	public CharacterClassId() {
		// TODO Auto-generated constructor stub
	}

	public Long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Long characterId) {
		this.characterId = characterId;
	}

	public Long getClassId() {
		return classId;
	}

	public void setClassId(Long classId) {
		this.classId = classId;
	}

}
