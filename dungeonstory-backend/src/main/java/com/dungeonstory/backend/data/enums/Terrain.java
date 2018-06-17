package com.dungeonstory.backend.data.enums;

import com.dungeonstory.backend.Labels;

public enum Terrain implements I18nEnum {

    ARCTIC,
    COAST,
    DESERT,
    FOREST,
    GRASSLAND,
    MOUNTAIN,
    SWAMP,
    UNDERDARK;

    private Terrain() {

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
