package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.ArmorType;
import com.dungeonstory.backend.factory.Factory;

public class ArmorTypeFactory implements Factory<ArmorType> {

    public ArmorTypeFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public ArmorType create() {
        return new ArmorType();
    }

}
