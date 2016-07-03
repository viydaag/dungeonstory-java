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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((characterId == null) ? 0 : characterId.hashCode());
		result = prime * result + ((equipmentId == null) ? 0 : equipmentId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CharacterEquipmentId)) {
			return false;
		}
		CharacterEquipmentId other = (CharacterEquipmentId) obj;
		if (characterId == null) {
			if (other.characterId != null) {
				return false;
			}
		} else if (!characterId.equals(other.characterId)) {
			return false;
		}
		if (equipmentId == null) {
			if (other.equipmentId != null) {
				return false;
			}
		} else if (!equipmentId.equals(other.equipmentId)) {
			return false;
		}
		return true;
	}

}
