package com.dungeonstory.backend.repository.mock;

import java.util.List;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.mock.MockDataGenerator;

public class MockShopRepository extends MockAbstractRepository<Shop> {

    private static Long idShop = 1L;

    public MockShopRepository() {
        super();
    }

    @Override
    public void init() {
//        List<Shop> list = MockDataGenerator.createShops();
//        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Shop entity) {
        if (entity.getId() == null) {
            entity.setId(idShop++);
        }
    }

}
