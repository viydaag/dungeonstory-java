package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.factory.Factory;

public class WeaponTypeFactory implements Factory<WeaponType> {

    private static final long serialVersionUID = -5121738886707976635L;

    @Override
    public WeaponType create() {
        return new WeaponType();
    }

}
