package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Region;
import com.dungeonstory.backend.factory.Factory;

public class RegionFactory implements Factory<Region> {

    public RegionFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Region create() {
        return new Region();
    }

}
