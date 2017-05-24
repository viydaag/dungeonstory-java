package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.WeaponType;
import com.dungeonstory.backend.factory.impl.WeaponTypeFactory;
import com.dungeonstory.backend.repository.impl.WeaponTypeRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.WeaponTypeDataService;

public class WeaponTypeService extends AbstractDataService<WeaponType, Long> implements WeaponTypeDataService {

    private static final long serialVersionUID = -7558187772714540120L;
	
    private static WeaponTypeService instance = null;

    public static synchronized WeaponTypeService getInstance() {
        if (instance == null) {
            instance = new WeaponTypeService();
        }
        return instance;
    }

    private WeaponTypeService() {
        super();
        setEntityFactory(new WeaponTypeFactory());
        setRepository(new WeaponTypeRepository());
    }

}
