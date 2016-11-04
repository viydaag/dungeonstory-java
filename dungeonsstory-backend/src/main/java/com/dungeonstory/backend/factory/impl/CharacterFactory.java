package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.factory.Factory;
import com.dungeonstory.backend.service.impl.LevelService;

public class CharacterFactory implements Factory<Character> {

    private static final long serialVersionUID = -383519940092638358L;

    @Override
    public Character create() {
        Character c = new Character();
        c.setLevel(LevelService.getInstance().read(1L));
        return c;
    }

}
