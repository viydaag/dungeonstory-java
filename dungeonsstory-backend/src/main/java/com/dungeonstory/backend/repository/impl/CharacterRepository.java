package com.dungeonstory.backend.repository.impl;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.repository.AbstractRepository;

public class CharacterRepository extends AbstractRepository<Character, Long> {

    private static final long serialVersionUID = 6507158340356635917L;

    @Override
    protected Class<Character> getEntityClass() {
        return Character.class;
    }

}
