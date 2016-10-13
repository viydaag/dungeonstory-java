package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.factory.Factory;

public class ShopFactory implements Factory<Shop> {

    private static final long serialVersionUID = -9100612498503496773L;

    @Override
    public Shop create() {
        return new Shop();
    }

}
