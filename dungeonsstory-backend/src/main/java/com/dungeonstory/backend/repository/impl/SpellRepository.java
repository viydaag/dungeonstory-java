package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Spell;
import com.dungeonstory.backend.repository.AbstractRepository;

public class SpellRepository extends AbstractRepository<Spell, Long> {

    @Override
    protected Class<Spell> getEntityClass() {
        return Spell.class;
    }

}
