package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.factory.Factory;

public class FeatFactory implements Factory<Feat> {

    public FeatFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Feat create() {
        return new Feat();
    }

}
