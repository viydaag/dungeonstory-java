package com.dungeonstory.backend.data.enums;

import com.dungeonstory.backend.Labels;

public enum Alignment implements I18nEnum {

    LAWFUL_GOOD(true),
    NEUTRAL_GOOD(true),
    CHAOTIC_GOOD(true),
    LAWFUL_NEUTRAL(true),
    NEUTRAL(true),
    CHAOTIC_NEUTRAL(true),
    LAWFUL_EVIL(true),
    NEUTRAL_EVIL(true),
    CHAOTIC_EVIL(true),
    UNALIGNED(false);
    
    private boolean isPlayable;
    
    private Alignment(boolean isPlayable) {
        this.isPlayable = isPlayable;
    }

    @Override
    public String getName() {
        return Labels.getInstance().getMessage(getKey(name(), "name"));
    }
    
    public String getAbbreviation() {
        return Labels.getInstance().getMessage(getKey(name(), "abbr"));
    }
    
    public String getDescription() {
        return Labels.getInstance().getMessage(getKey(name(), "description"));
    }
    
    @Override
    public String toString() {
        return getName();
    }

    public boolean isPlayable() {
        return isPlayable;
    }

}
