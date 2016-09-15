package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.factory.Factory;

public class CityFactory implements Factory<City> {

    private static final long serialVersionUID = -1006519133715738578L;

    @Override
    public City create() {
        return new City();
    }

}
