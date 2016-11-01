package com.dungeonstory.backend.data;

import java.io.Serializable;

public class CharacterClassId implements Serializable {

    private static final long serialVersionUID = 6205978746903824877L;

    private Long character;

    private Long classe;

    public CharacterClassId() {

    }

    public Long getCharacter() {
        return character;
    }

    public void setCharacter(Long characterId) {
        this.character = characterId;
    }

    public Long getClasse() {
        return classe;
    }

    public void setClasse(Long classId) {
        this.classe = classId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((character == null) ? 0 : character.hashCode());
        result = prime * result + ((classe == null) ? 0 : classe.hashCode());
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
        if (character == null) {
            if (other.character != null) {
                return false;
            }
        } else if (!character.equals(other.character)) {
            return false;
        }
        if (classe == null) {
            if (other.classe != null) {
                return false;
            }
        } else if (!classe.equals(other.classe)) {
            return false;
        }
        return true;
    }

}
