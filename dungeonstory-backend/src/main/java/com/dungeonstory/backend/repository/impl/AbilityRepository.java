package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.repository.AbstractRepository;

public class AbilityRepository extends AbstractRepository<Ability, Long> {

    private static final long serialVersionUID = -1785503539532635011L;

    @Override
    protected Class<Ability> getEntityClass() {
        return Ability.class;
    }

}
