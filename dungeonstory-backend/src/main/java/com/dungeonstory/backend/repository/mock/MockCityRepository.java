package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockCityRepository extends MockAbstractRepository<City> {

    private static Long idCity = 1L;

    public MockCityRepository() {
        super();
    }

    @Override
    public void init() {
        List<City> list = MockDataGenerator.createCities();
        list.stream().forEach(this::create);
    }

    @Override
    public void setId(City entity) {
        if (entity.getId() == null) {
            entity.setId(idCity++);
        }
    }

}
