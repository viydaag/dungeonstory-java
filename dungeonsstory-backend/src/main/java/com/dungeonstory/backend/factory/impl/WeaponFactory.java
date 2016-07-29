package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Weapon;
import com.dungeonstory.backend.factory.Factory;

public class WeaponFactory implements Factory<Weapon> {

    @Override
    public Weapon create() {
        return new Weapon();
    }

}
