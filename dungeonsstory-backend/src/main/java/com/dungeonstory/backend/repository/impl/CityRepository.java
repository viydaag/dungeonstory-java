package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.repository.AbstractRepository;

public class CityRepository extends AbstractRepository<City, Long> {

    private static final long serialVersionUID = 8023129490074502294L;

    @Override
    protected Class<City> getEntityClass() {
        return City.class;
    }

}
