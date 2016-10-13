package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.factory.Factory;

public class AbilityFactory implements Factory<Ability> {

    private static final long serialVersionUID = 8867569203555062817L;

    @Override
    public Ability create() {
        return new Ability();
    }

}
