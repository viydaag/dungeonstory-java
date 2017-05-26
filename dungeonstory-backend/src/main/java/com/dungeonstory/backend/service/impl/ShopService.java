package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.repository.impl.ShopRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.ShopDataService;

public class ShopService extends AbstractDataService<Shop, Long> implements ShopDataService {

    private static final long serialVersionUID = 4967508150181211443L;

    private static ShopService instance = null;

    public static synchronized ShopService getInstance() {
        if (instance == null) {
            instance = new ShopService();
        }
        return instance;
    }

    private ShopService() {
        super();
        setEntityFactory(() -> new Shop());
        setRepository(new ShopRepository());
    }

}
