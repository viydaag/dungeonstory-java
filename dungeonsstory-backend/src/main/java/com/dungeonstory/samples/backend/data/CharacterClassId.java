package com.dungeonstory.samples.backend.data;

import java.io.Serializable;

public class CharacterClassId implements Serializable {

	private static final long serialVersionUID = 6205978746903824877L;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((characterId == null) ? 0 : characterId.hashCode());
		result = prime * result + ((classId == null) ? 0 : classId.hashCode());
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
		if (!(obj instanceof CharacterClassId)) {
			return false;
		}
		CharacterClassId other = (CharacterClassId) obj;
		if (characterId == null) {
			if (other.characterId != null) {
				return false;
			}
		} else if (!characterId.equals(other.characterId)) {
			return false;
		}
		if (classId == null) {
			if (other.classId != null) {
				return false;
			}
		} else if (!classId.equals(other.classId)) {
			return false;
		}
		return true;
	}

}
