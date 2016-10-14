package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.factory.Factory;

public class CharacterFactory implements Factory<Character> {

    private static final long serialVersionUID = -383519940092638358L;

    @Override
    public Character create() {
        return new Character();
    }

}
