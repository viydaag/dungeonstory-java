package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.factory.Factory;

public class AbilityFactory implements Factory<Ability> {

    public AbilityFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Ability create() {
        return new Ability();
    }

}
