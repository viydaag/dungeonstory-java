package com.dungeonstory.backend.data;

import java.io.Serializable;

public class CharacterBackgroundId implements Serializable {

    private static final long serialVersionUID = -8363116091915479762L;

    private Long character;

    private Long background;

    public CharacterBackgroundId() {

    }

    public Long getCharacter() {
        return character;
    }

    public void setCharacter(Long character) {
        this.character = character;
    }

    public Long getBackground() {
        return background;
    }

    public void setBackground(Long background) {
        this.background = background;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((background == null) ? 0 : background.hashCode());
        result = prime * result + ((character == null) ? 0 : character.hashCode());
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
        if (!(obj instanceof CharacterBackgroundId)) {
            return false;
        }
        CharacterBackgroundId other = (CharacterBackgroundId) obj;
        if (background == null) {
            if (other.background != null) {
                return false;
            }
        } else if (!background.equals(other.background)) {
            return false;
        }
        if (character == null) {
            if (other.character != null) {
                return false;
            }
        } else if (!character.equals(other.character)) {
            return false;
        }
        return true;
    }

}
