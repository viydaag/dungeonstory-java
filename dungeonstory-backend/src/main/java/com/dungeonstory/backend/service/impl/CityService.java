package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.City;
import com.dungeonstory.backend.factory.impl.CityFactory;
import com.dungeonstory.backend.repository.impl.CityRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.CityDataService;

public class CityService extends AbstractDataService<City, Long> implements CityDataService {

	private static final long serialVersionUID = 4726207439928489734L;
	
	private static CityService instance = null;

    public static synchronized CityService getInstance() {
        if (instance == null) {
            instance = new CityService();
        }
        return instance;
    }

    private CityService() {
        super();
        setEntityFactory(new CityFactory());
        setRepository(new CityRepository());
    }

}
