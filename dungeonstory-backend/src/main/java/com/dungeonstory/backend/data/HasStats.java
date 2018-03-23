package com.dungeonstory.backend.data;

import java.util.Set;
import com.dungeonstory.backend.data.enums.Ability;

public interface HasStats {

    public int getStrength();

    public int getDexterity();

    public int getConstitution();

    public int getIntelligence();

    public int getWisdom();

    public int getCharisma();
    
    public Set<Ability> getSavingThrowProficiencies();

}
