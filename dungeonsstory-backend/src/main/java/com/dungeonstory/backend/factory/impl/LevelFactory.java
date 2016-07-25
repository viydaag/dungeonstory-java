package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.factory.Factory;

public class LevelFactory implements Factory<Level> {

    public LevelFactory() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Level create() {
        return new Level();
    }

}
