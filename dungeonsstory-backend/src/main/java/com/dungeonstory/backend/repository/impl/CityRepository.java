package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.repository.AbstractRepository;

public class CityRepository extends AbstractRepository<City, Long> {

    @Override
    protected Class<City> getEntityClass() {
        return City.class;
    }

}
