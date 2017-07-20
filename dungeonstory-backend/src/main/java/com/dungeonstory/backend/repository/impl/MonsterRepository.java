package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Monster;
import com.dungeonstory.backend.repository.AbstractRepository;

public class MonsterRepository extends AbstractRepository<Monster, Long> {

    private static final long serialVersionUID = -5113795003173589074L;

    @Override
    protected Class<Monster> getEntityClass() {
        return Monster.class;
    }

}
