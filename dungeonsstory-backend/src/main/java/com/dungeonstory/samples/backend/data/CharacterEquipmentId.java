package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

public class CharacterEquipmentId implements Serializable {
	
	private static final long serialVersionUID = 4916486290491053123L;

	private Long characterId;

	private Long equipmentId;
	
	public CharacterEquipmentId() {
		super();
	}

	public Long getCharacterId() {
		return characterId;
	}

	public void setCharacterId(Long characterId) {
		this.characterId = characterId;
	}

	public Long getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}

}
