package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Feat;
import com.dungeonstory.backend.factory.impl.FeatFactory;
import com.dungeonstory.backend.repository.impl.FeatRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class FeatService extends AbstractDataService<Feat, Long> {

    private static FeatService instance = null;

    public static synchronized FeatService getInstance() {
        if (instance == null) {
            instance = new FeatService();
        }
        return instance;
    }

    private FeatService() {
        super();
        setEntityFactory(new FeatFactory());
        setRepository(new FeatRepository());
    }

}
