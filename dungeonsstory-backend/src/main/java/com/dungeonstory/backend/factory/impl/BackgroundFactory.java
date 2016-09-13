package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Background;
import com.dungeonstory.backend.factory.Factory;

public class BackgroundFactory implements Factory<Background> {

    public BackgroundFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Background create() {
        return new Background();
    }

}
