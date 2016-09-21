package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.factory.Factory;

public class CreatureTypeFactory implements Factory<CreatureType> {

    private static final long serialVersionUID = 1494516218039821986L;

    @Override
    public CreatureType create() {
        return new CreatureType();
    }

}
