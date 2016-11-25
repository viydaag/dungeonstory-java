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
        c.setExperience(0);
        c.setArmorClass(10);
        c.setStrength(1);
        c.setDexterity(1);
        c.setConstitution(1);
        c.setIntelligence(1);
        c.setWisdom(1);
        c.setCharisma(1);
        return c;
    }

}
