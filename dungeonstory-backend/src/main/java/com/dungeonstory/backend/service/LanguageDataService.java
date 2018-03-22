package com.dungeonstory.backend.service;

import java.util.Set;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Race;
import com.dungeonstory.backend.data.enums.Language;

public interface LanguageDataService {

    public Set<Language> getLanguagesNotInRace(Race race);

    public Set<Language> getUnassignedLanguages(Character character);

}
