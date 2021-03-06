package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.CreatureType;
import com.dungeonstory.backend.repository.impl.CreatureTypeRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.CreatureTypeDataService;

public class CreatureTypeService extends AbstractDataService<CreatureType, Long> implements CreatureTypeDataService {

    private static final long serialVersionUID = 1998150224869295041L;

    private static CreatureTypeService instance = null;

    public static synchronized CreatureTypeService getInstance() {
        if (instance == null) {
            instance = new CreatureTypeService();
        }
        return instance;
    }

    private CreatureTypeService() {
        super();
        setEntityFactory(() -> new CreatureType());
        setRepository(new CreatureTypeRepository());
    }

}
