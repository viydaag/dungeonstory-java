package com.dungeonstory.backend.service;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Feats;

public interface CharacterDataService extends DataService<Character, Long> {

    public CharacterClass getAssignedClass(Character character, DSClass classe);

    public void levelUp(Character character);

    public boolean hasFeat(Character character, Feats feat);
}
