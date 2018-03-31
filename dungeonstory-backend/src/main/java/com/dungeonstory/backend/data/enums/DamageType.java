package com.dungeonstory.backend.data.enums;

import com.dungeonstory.backend.Labels;

public enum DamageType implements I18nEnum {
    
    ACID,
    BLUDGEONING,
    FIRE,
    COLD,
    FORCE,
    LIGHTNING,
    NECROTIC,
    PERCING,
    POISON,
    PSYCHIC,
    RADIANT,
    SLASHING,
    THUNDER;

    @Override
    public String getName() {
        return Labels.getInstance().getMessage(getKey(name(), "name"));
    }
    
    public String getDescription() {
        return Labels.getInstance().getMessage(getKey(name(), "description"));
    }
    
    @Override
    public String toString() {
        return getName();
    }

}
