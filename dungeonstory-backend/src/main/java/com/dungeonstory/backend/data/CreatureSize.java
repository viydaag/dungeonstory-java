package com.dungeonstory.backend.data;

public enum CreatureSize {

    TINY("TP", "Très petit", 2),
    SMALL("P", "Petit", 5),
    MEDIUM("M", "Moyen", 5),
    LARGE("G", "Grand", 10),
    HUGE("TG", "Très grand", 15),
    GARGANTUAN("Gig", "Gigantesque", 20);

    private String abbreviation;
    private String name;
    private int    spaceInFeet;

    private CreatureSize(String abbreviation, String name, int spaceInFeet) {
        this.abbreviation = abbreviation;
        this.name = name;
        this.spaceInFeet = spaceInFeet;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getName() {
        return name;
    }

    public int getSpaceInFeet() {
        return spaceInFeet;
    }

    @Override
    public String toString() {
        return getName();
    }

}
