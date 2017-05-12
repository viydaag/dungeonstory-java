package com.dungeonstory.backend.service.mock;

import java.util.List;
import java.util.stream.Collectors;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.factory.impl.CharacterFactory;
import com.dungeonstory.backend.repository.impl.CharacterRepository;
import com.dungeonstory.backend.repository.mock.MockCharacterRepository;
import com.dungeonstory.backend.service.AbstractDataService;
import com.dungeonstory.backend.service.CharacterDataService;

public class MockCharacterService extends AbstractDataService<Character, Long> implements CharacterDataService {

    private static final long serialVersionUID = -406900858127002126L;
    
    private static MockCharacterService instance         = null;

    public static synchronized MockCharacterService getInstance() {
        if (instance == null) {
            instance = new MockCharacterService();
        }
        return instance;
    }

    private MockCharacterService() {
        super();
        setEntityFactory(new CharacterFactory());
        setRepository(new MockCharacterRepository());
    }

    @Override
    public CharacterClass getAssignedClass(Character character, DSClass classe) {
        return ((MockCharacterRepository) entityRepository).getAssignedClass(character, classe);
    }

}
