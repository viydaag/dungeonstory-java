package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Armor;
import com.dungeonstory.backend.factory.Factory;

public class ArmorFactory implements Factory<Armor> {

    @Override
    public Armor create() {
        return new Armor();
    }

}
