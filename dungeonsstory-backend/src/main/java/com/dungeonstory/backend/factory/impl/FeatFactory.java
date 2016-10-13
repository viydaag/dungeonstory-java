package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.factory.Factory;

public class FeatFactory implements Factory<Feat> {

    private static final long serialVersionUID = 2923886149752014080L;

    @Override
    public Feat create() {
        return new Feat();
    }

}
