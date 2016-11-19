package com.dungeonstory.backend.repository.mock;

import com.dungeonstory.backend.data.City;

public class MockCityRepository extends MockAbstractRepository<City> {

    private static Long idCity = 1L;

    public MockCityRepository() {
        super();
    }

    @Override
    public void init() {
//        List<City> list = MockDataGenerator.createCities();
//        list.stream().forEach(this::create);
    }

    @Override
    public void setId(City entity) {
        if (entity.getId() == null) {
            entity.setId(idCity++);
        }
    }

}
