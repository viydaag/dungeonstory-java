package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Background;
import com.dungeonstory.backend.factory.Factory;

public class BackgroundFactory implements Factory<Background> {

    private static final long serialVersionUID = 3055122443181874333L;

    @Override
    public Background create() {
        return new Background();
    }

}
