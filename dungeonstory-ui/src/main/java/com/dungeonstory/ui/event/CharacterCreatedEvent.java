package com.dungeonstory.ui.event;

import com.dungeonstory.backend.data.Character;

public class CharacterCreatedEvent {

    private Character character;

    public CharacterCreatedEvent(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }

}
