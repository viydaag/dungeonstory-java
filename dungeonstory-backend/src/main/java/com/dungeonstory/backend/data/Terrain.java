package com.dungeonstory.backend.data;

public enum Terrain {

    ARCTIC("Arctique"),
    COAST("Littoral"),
    DESERT("Désert"),
    FOREST("Forêt"),
    GRASSLAND("Plaine"),
    MOUNTAIN("Montagne"),
    SWAMP("Marais"),
    UNDERDARK("Outreterre");

    private String name;

    private Terrain(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return getName();
    }

}
