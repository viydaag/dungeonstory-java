package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.repository.AbstractRepository;

public class ShopRepository extends AbstractRepository<Shop, Long> {

    private static final long serialVersionUID = -3522397314852161478L;

    @Override
    protected Class<Shop> getEntityClass() {
        return Shop.class;
    }

}
