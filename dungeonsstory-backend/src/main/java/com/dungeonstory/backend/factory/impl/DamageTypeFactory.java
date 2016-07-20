package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.factory.Factory;

public class DamageTypeFactory implements Factory<DamageType> {

    public DamageTypeFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public DamageType create() {
        return new DamageType();
    }

}
