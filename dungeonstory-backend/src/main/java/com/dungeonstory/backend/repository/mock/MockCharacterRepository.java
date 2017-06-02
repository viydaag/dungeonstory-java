package com.dungeonstory.backend.repository.mock;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.DSClass;

public class MockCharacterRepository extends MockAbstractRepository<Character> {

    private static Long idCharacter = 1L;

    public MockCharacterRepository() {
        super();
    }

    @Override
    public void init() {
//        List<Character> list = MockDataGenerator.createCharacters();
//        list.stream().forEach(this::create);
    }

    @Override
    public void setId(Character entity) {
        if (entity.getId() == null) {
            entity.setId(idCharacter++);
        }
    }

    public CharacterClass getAssignedClass(Character character, DSClass classe) {
        return entities.get(character).getClasses().stream().filter(aClass -> aClass.getClasse().equals(classe)).findFirst().orElse(null);
    }

}
