package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.repository.AbstractRepository;

public class AbilityRepository extends AbstractRepository<Ability, Long> {

    @Override
    protected Class<? extends Ability> getEntityClass() {
        return Ability.class;
    }

}
