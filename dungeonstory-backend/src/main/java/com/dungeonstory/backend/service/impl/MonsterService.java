package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Monster;
import com.dungeonstory.backend.repository.impl.MonsterRepository;
import com.dungeonstory.backend.service.MonsterDataService;
import com.dungeonstory.backend.service.AbstractDataService;

public class MonsterService extends AbstractDataService<Monster, Long> implements MonsterDataService {

    private static final long serialVersionUID = 3074086216670399119L;
    
    private static MonsterService instance = null;

    public static synchronized MonsterService getInstance() {
        if (instance == null) {
            instance = new MonsterService();
        }
        return instance;
    }

    private MonsterService() {
        super();
        setEntityFactory(() -> new Monster());
        setRepository(new MonsterRepository());
    }

}
