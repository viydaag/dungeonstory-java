package com.dungeonstory.backend.service.impl;

import javax.persistence.NoResultException;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.enums.Feat;
import com.dungeonstory.backend.factory.impl.CharacterFactory;
import com.dungeonstory.backend.repository.impl.CharacterRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.CharacterDataService;

public class CharacterService extends AbstractDataService<Character, Long> implements CharacterDataService {

    private static final long serialVersionUID = -1946519936437060467L;

    private static CharacterService instance = null;

    private CharacterRepository repository;

    public static synchronized CharacterService getInstance() {
        if (instance == null) {
            instance = new CharacterService();
        }
        return instance;
    }

    private CharacterService() {
        super();
        setEntityFactory(new CharacterFactory());
        repository = new CharacterRepository();
        setRepository(repository);
    }

    @Override
    public CharacterClass getAssignedClass(Character character, DSClass classe) {
        try {
            return repository.getAssignedClass(character, classe);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void levelUp(Character character) {
        Level levelUp = LevelService.getInstance().read(character.getLevel().getId() + 1);
        character.setLevel(levelUp);
        update(character);
    }

    @Override
    public boolean hasFeat(Character character, Feat feat) {
        return repository.hasFeat(character.getId(), feat);
    }

}
