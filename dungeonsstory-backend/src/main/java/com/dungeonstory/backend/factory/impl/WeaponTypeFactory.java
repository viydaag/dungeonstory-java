package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.factory.Factory;

public class WeaponTypeFactory implements Factory<WeaponType> {

    public WeaponTypeFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public WeaponType create() {
        return new WeaponType();
    }

}
