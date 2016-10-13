package com.dungeonstory.backend.factory.impl;

import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.factory.Factory;

public class LevelFactory implements Factory<Level> {

    private static final long serialVersionUID = -4976308023197528370L;

    @Override
    public Level create() {
        return new Level();
    }

}
