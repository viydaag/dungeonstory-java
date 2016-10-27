package com.dungeonstory.backend.service;

import java.util.List;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.Language;
import com.dungeonstory.backend.data.Race;

public interface LanguageDataService {

    public List<Language> getLanguagesNotInRace(Race race);

    public List<Language> getUnassignedLanguages(Character character);

}
