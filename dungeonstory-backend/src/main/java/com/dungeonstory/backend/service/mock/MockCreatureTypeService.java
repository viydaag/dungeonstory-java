package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.repository.mock.MockCreatureTypeRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.CreatureTypeDataService;

public class MockCreatureTypeService extends AbstractDataService<CreatureType, Long> implements CreatureTypeDataService {

    private static final long serialVersionUID = -946377047509651355L;
    
    private static MockCreatureTypeService instance = null;

    public static synchronized MockCreatureTypeService getInstance() {
        if (instance == null) {
            instance = new MockCreatureTypeService();
        }
        return instance;
    }

    private MockCreatureTypeService() {
        super();
        setEntityFactory(() -> new CreatureType());
        setRepository(new MockCreatureTypeRepository());
    }

}
