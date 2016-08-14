package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.factory.Factory;

public class ShopFactory implements Factory<Shop> {

    public ShopFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Shop create() {
        return new Shop();
    }

}
