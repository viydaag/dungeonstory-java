package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.factory.Factory;

public class CityFactory implements Factory<City> {

    @Override
    public City create() {
        return new City();
    }

}
