package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Armor;
import com.dungeonstory.backend.repository.AbstractRepository;

public class ArmorRepository extends AbstractRepository<Armor, Long> {

    @Override
    protected Class<Armor> getEntityClass() {
        return Armor.class;
    }

}
