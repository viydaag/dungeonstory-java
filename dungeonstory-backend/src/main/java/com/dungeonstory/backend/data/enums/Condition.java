package com.dungeonstory.backend.data.enums;

import com.dungeonstory.backend.Labels;

public enum Condition implements I18nEnum {
    
    BLINDED,
    CHARMED,
    CURSED,
    DEAFENED,
    FRIGHTENED,
    GRAPPLED,
    INCAPACITATED,
    INVISIBLE,
    MUTED,
    PARALYZED,
    PETRIFIED,
    POISONED,
    PRONE,
    RESTRAINED,
    SLEEP,
    STUNNED,
    UNCONSCIOUS;
    
    private Condition() {
        
    }
    
    @Override
    public String getName() {
        return Labels.getInstance().getMessage(getKey(name(), "name"));
    }
    
    @Override
    public String toString() {
        return getName();
    }

}
