package com.dungeonstory.backend.service;

import java.util.List;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Feat;

public interface FeatDataService extends DataService<Feat, Long> {

    public List<Feat> findAllFeats();
    
    public List<Feat> findAllClassFeatures();
    
    public List<Feat> findAllFeatsExcept(Feat feat);
    
    public List<Feat> findAllUnassignedFeats(Character character);
	
}
