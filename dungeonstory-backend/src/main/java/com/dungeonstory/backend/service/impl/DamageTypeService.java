package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.DamageType;
import com.dungeonstory.backend.factory.impl.DamageTypeFactory;
import com.dungeonstory.backend.repository.impl.DamageTypeRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class DamageTypeService extends AbstractDataService<DamageType, Long> {

	private static final long serialVersionUID = 661540660335270613L;
	
	private static DamageTypeService instance = null;

    public static synchronized DamageTypeService getInstance() {
        if (instance == null) {
            instance = new DamageTypeService();
        }
        return instance;
    }

    private DamageTypeService() {
        super();
        setEntityFactory(new DamageTypeFactory());
        setRepository(new DamageTypeRepository());
    }

}
