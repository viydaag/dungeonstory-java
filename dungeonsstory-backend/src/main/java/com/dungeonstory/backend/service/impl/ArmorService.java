package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Armor;
import com.dungeonstory.backend.factory.impl.ArmorFactory;
import com.dungeonstory.backend.repository.impl.ArmorRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class ArmorService extends AbstractDataService<Armor, Long> {

    private static ArmorService instance = null;

    public static synchronized ArmorService getInstance() {
        if (instance == null) {
            instance = new ArmorService();
        }
        return instance;
    }

    private ArmorService() {
        super();
        setEntityFactory(new ArmorFactory());
        setRepository(new ArmorRepository());
    }

}
