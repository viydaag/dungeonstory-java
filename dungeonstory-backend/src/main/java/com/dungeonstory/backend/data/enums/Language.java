package com.dungeonstory.backend.data.enums;

import com.dungeonstory.backend.Labels;

public enum Language implements I18nEnum {

    COMMON,
    DWARVISH,
    ELVISH,
    GIANT,
    GNOMISH,
    GOBLIN,
    HALFLING,
    ORC,
    ABYSSAL,
    CELESTIAL,
    DRACONIC,
    DEEP_SPEECH,
    INFERNAL,
    PRIMORDIAL,
    SYLVAN,
    UNDERCOMMON;

    @Override
    public String getName() {
        return Labels.getInstance().getMessage(getKey(name(), "name"));
    }

    public String getScript() {
        return Labels.getInstance().getMessage(getKey(name(), "script"));
    }

}
