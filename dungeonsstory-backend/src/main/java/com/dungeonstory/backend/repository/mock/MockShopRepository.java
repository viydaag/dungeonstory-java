package com.dungeonstory.backend.repository.mock;

import com.dungeonstory.backend.data.Shop;

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
