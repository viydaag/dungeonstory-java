package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.factory.Factory;

public class RaceFactory implements Factory<Race> {

    public RaceFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Race create() {
        return new Race();
    }

}
