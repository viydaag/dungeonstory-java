package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.factory.Factory;

public class RegionFactory implements Factory<Region> {

    private static final long serialVersionUID = 482850487986165771L;

    @Override
    public Region create() {
        return new Region();
    }

}
