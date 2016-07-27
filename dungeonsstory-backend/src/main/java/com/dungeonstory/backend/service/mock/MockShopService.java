package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.factory.impl.ShopFactory;
import com.dungeonstory.backend.repository.mock.MockShopRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class MockShopService extends AbstractDataService<Shop, Long> {

    private static MockShopService instance = null;

    public static synchronized MockShopService getInstance() {
        if (instance == null) {
            instance = new MockShopService();
        }
        return instance;
    }

    private MockShopService() {
        super();
        setEntityFactory(new ShopFactory());
        setRepository(new MockShopRepository());
    }

}
