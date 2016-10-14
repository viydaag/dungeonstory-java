package com.dungeonstory.backend.service.impl;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.factory.impl.CharacterFactory;
import com.dungeonstory.backend.repository.impl.CharacterRepository;
import com.dungeonstory.backend.service.AbstractDataService;

public class CharacterService extends AbstractDataService<Character, Long> {

    private static final long serialVersionUID = -1946519936437060467L;

    private static CharacterService instance = null;

    public static synchronized CharacterService getInstance() {
        if (instance == null) {
            instance = new CharacterService();
        }
        return instance;
    }

    private CharacterService() {
        super();
        setEntityFactory(new CharacterFactory());
        setRepository(new CharacterRepository());
    }

}
