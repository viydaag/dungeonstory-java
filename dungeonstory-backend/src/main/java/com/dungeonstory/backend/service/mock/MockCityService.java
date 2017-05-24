package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.factory.impl.CityFactory;
import com.dungeonstory.backend.repository.mock.MockCityRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.CityDataService;

public class MockCityService extends AbstractDataService<City, Long> implements CityDataService {

    private static final long      serialVersionUID = -4844097587498289999L;

    private static MockCityService instance         = null;

    public static synchronized MockCityService getInstance() {
        if (instance == null) {
            instance = new MockCityService();
        }
        return instance;
    }

    private MockCityService() {
        super();
        setEntityFactory(new CityFactory());
        setRepository(new MockCityRepository());
    }

}
