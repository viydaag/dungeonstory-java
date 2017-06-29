package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Temple;
import com.dungeonstory.backend.repository.impl.TempleRepository;
import com.dungeonstory.backend.service.TempleDataService;
import com.dungeonstory.backend.service.AbstractDataService;

public class TempleService extends AbstractDataService<Temple, Long> implements TempleDataService {

    private static final long serialVersionUID = 7219972356913325845L;
    
    private static TempleService instance = null;

    public static synchronized TempleService getInstance() {
        if (instance == null) {
            instance = new TempleService();
        }
        return instance;
    }

    private TempleService() {
        super();
        setEntityFactory(() -> new Temple());
        setRepository(new TempleRepository());
    }

}
