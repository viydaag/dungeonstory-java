package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Background;
import com.dungeonstory.backend.factory.impl.BackgroundFactory;
import com.dungeonstory.backend.repository.impl.BackgroundRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class BackgroundService extends AbstractDataService<Background, Long> {

    private static final long serialVersionUID = -2912947409014871356L;
    
    private static BackgroundService instance = null;

    public static synchronized BackgroundService getInstance() {
        if (instance == null) {
            instance = new BackgroundService();
        }
        return instance;
    }

    private BackgroundService() {
        super();
        setEntityFactory(new BackgroundFactory());
        setRepository(new BackgroundRepository());
    }

}
