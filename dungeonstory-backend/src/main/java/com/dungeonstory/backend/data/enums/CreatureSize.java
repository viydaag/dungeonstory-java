package com.dungeonstory.backend.data.enums;

import com.dungeonstory.backend.Labels;

public enum CreatureSize implements I18nEnum {

    TINY( 2),
    SMALL(5),
    MEDIUM(5),
    LARGE(10),
    HUGE(15),
    GARGANTUAN(20);

    private int spaceInFeet;

    private CreatureSize(int spaceInFeet) {
        this.spaceInFeet = spaceInFeet;
    }

    public String getAbbreviation() {
        return Labels.getInstance().getMessage(getKey(name(), "abbreviation"));
    }

    @Override
    public String getName() {
        return Labels.getInstance().getMessage(getKey(name(), "name"));
    }

    public int getSpaceInFeet() {
        return spaceInFeet;
    }

    @Override
    public String toString() {
        return getName();
    }

}
