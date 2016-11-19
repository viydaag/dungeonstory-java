package com.dungeonstory.backend.data;

import java.io.Serializable;

public class CharacterEquipmentId implements Serializable {

    private static final long serialVersionUID = 4916486290491053123L;

    private Long character;

    private Long equipment;

    public CharacterEquipmentId() {
        super();
    }

    public Long getCharacter() {
        return character;
    }

    public void setCharacter(Long characterId) {
        this.character = characterId;
    }

    public Long getEquipment() {
        return equipment;
    }

    public void setEquipment(Long equipmentId) {
        this.equipment = equipmentId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((character == null) ? 0 : character.hashCode());
        result = prime * result + ((equipment == null) ? 0 : equipment.hashCode());
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
        if (character == null) {
            if (other.character != null) {
                return false;
            }
        } else if (!character.equals(other.character)) {
            return false;
        }
        if (equipment == null) {
            if (other.equipment != null) {
                return false;
            }
        } else if (!equipment.equals(other.equipment)) {
            return false;
        }
        return true;
    }

}
