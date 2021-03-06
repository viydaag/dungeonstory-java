package com.dungeonstory.backend.service.mock;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.data.enums.Feat;
import com.dungeonstory.backend.factory.impl.CharacterFactory;
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

    @Override
    public void levelUp(Character character) {
        Level levelUp = MockLevelService.getInstance().read(character.getLevel().getId() + 1);
        character.setLevel(levelUp);
        update(character);
    }

    @Override
    public boolean hasFeat(Character character, Feat feat) {
        return character.getFeats().contains(feat);
    }

    @Override
    public boolean isAbleToLevelUp(Character character) {
        // TODO Auto-generated method stub
        return false;
    }

}
