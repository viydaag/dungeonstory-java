package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.factory.impl.ShopFactory;
import com.dungeonstory.backend.repository.mock.MockShopRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.ShopDataService;

public class MockShopService extends AbstractDataService<Shop, Long> implements ShopDataService {

	private static final long serialVersionUID = 4229387191031685630L;
	
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
