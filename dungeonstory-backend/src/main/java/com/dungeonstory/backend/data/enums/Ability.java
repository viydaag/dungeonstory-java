package com.dungeonstory.backend.data.enums;

import com.dungeonstory.backend.Labels;

public enum Ability implements I18nEnum {

    STRENGTH,
    DEXTERITY,
    CONSTITUTION,
    INTELLIGENCE,
    WISDOM,
    CHARISMA;

    @Override
    public String getName() {
        return Labels.getInstance().getMessage(getKey(name().substring(0, 3), "caption"));
    }
    
    public String getAbbreviation() {
        return Labels.getInstance().getMessage(getKey(name().substring(0, 3), "abbr"));
    }

}
