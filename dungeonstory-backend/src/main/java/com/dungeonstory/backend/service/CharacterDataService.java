package com.dungeonstory.backend.service;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.DSClass;

public interface CharacterDataService extends DataService<Character, Long> {

    public CharacterClass getAssignedClass(Character character, DSClass classe);

    public void levelUp(Character character);
}
