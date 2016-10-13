package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.repository.AbstractRepository;

public class LevelRepository extends AbstractRepository<Level, Long> {

    private static final long serialVersionUID = 4118177557498638334L;

    @Override
    protected Class<Level> getEntityClass() {
        return Level.class;
    }

}
