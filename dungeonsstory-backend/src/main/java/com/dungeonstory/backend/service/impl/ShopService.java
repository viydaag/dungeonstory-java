package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.factory.impl.ShopFactory;
import com.dungeonstory.backend.repository.impl.ShopRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class ShopService extends AbstractDataService<Shop, Long> {

    private static ShopService instance = null;

    public static synchronized ShopService getInstance() {
        if (instance == null) {
            instance = new ShopService();
        }
        return instance;
    }

    private ShopService() {
        super();
        setEntityFactory(new ShopFactory());
        setRepository(new ShopRepository());
    }

}
