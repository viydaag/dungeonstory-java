package com.dungeonstory.backend.service;

import java.util.Set;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.enums.Feat;

public interface FeatDataService {

    public Set<Feat> findAllFeatsExcept(Feat feat);
    
    public Set<Feat> findAllUnassignedFeats(Character character);

}
