package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Background;
import com.dungeonstory.backend.repository.AbstractRepository;

public class BackgroundRepository extends AbstractRepository<Background, Long> {

    @Override
    protected Class<Background> getEntityClass() {
        return Background.class;
    }

}
