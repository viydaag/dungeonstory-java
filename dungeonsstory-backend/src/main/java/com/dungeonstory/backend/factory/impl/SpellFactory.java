package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.factory.Factory;

public class SpellFactory implements Factory<Spell> {

    @Override
    public Spell create() {
        return new Spell();
    }

}
