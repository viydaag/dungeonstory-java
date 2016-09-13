package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Deity;
import com.dungeonstory.backend.factory.impl.DeityFactory;
import com.dungeonstory.backend.repository.impl.DeityRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class DeityService extends AbstractDataService<Deity, Long> {

    private static final long serialVersionUID = 6468595169787824606L;

    private static DeityService instance = null;

    public static synchronized DeityService getInstance() {
        if (instance == null) {
            instance = new DeityService();
        }
        return instance;
    }

    private DeityService() {
        super();
        setEntityFactory(new DeityFactory());
        setRepository(new DeityRepository());
    }

}
