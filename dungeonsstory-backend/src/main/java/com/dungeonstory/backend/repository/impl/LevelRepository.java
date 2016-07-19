package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.repository.AbstractRepository;

public class LevelRepository extends AbstractRepository<Level, Long> {

    @Override
    protected Class<Level> getEntityClass() {
        return Level.class;
    }

}
