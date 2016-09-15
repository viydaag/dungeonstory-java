package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.factory.Factory;

public class RaceFactory implements Factory<Race> {

    private static final long serialVersionUID = 4869051409399391962L;

    @Override
    public Race create() {
        return new Race();
    }

}
