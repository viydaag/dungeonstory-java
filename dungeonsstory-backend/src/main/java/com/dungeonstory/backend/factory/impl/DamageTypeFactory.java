package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.factory.Factory;

public class DamageTypeFactory implements Factory<DamageType> {

    private static final long serialVersionUID = -187501861822131589L;

    @Override
    public DamageType create() {
        return new DamageType();
    }

}
