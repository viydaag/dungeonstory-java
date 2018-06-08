package com.dungeonstory.backend.service;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.enums.Feat;

public interface CharacterDataService extends DataService<Character, Long> {

    public CharacterClass getAssignedClass(Character character, DSClass classe);

    public void levelUp(Character character);

    public boolean hasFeat(Character character, Feat feat);
    
    public boolean isAbleToLevelUp(Character character);
}
