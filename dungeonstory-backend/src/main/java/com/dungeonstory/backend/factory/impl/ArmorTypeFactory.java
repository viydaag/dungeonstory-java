package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.factory.Factory;

public class ArmorTypeFactory implements Factory<ArmorType> {

    private static final long serialVersionUID = 3282207230268884005L;

    @Override
    public ArmorType create() {
        return new ArmorType();
    }

}
