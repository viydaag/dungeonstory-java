package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Monster;
import com.dungeonstory.backend.repository.mock.MockMonsterRepository;
import com.dungeonstory.backend.service.MonsterDataService;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockMonsterService extends AbstractDataService<Monster, Long> implements MonsterDataService {

    private static final long serialVersionUID = -7477144524478742768L;
    
    private static MockMonsterService instance = null;

    public static synchronized MockMonsterService getInstance() {
        if (instance == null) {
            instance = new MockMonsterService();
        }
        return instance;
    }

    private MockMonsterService() {
        super();
        setEntityFactory(() -> new Monster());
        setRepository(new MockMonsterRepository());
    }

}
