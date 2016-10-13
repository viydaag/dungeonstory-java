package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.factory.Factory;

public class DeityFactory implements Factory<Deity> {

    private static final long serialVersionUID = -4183182201780915731L;

    @Override
    public Deity create() {
        return new Deity();
    }

}
