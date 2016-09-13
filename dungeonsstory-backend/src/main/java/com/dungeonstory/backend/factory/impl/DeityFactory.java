package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.factory.Factory;

public class DeityFactory implements Factory<Deity> {

    @Override
    public Deity create() {
        return new Deity();
    }

}
