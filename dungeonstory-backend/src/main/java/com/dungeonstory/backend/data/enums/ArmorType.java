package com.dungeonstory.backend.data.enums;

import com.dungeonstory.backend.Labels;

public enum ArmorType implements I18nEnum {

    PADDED(ProficiencyType.LIGHT, ArmorType.NO_MAX_DEX_BONUS, 1, true, 1, 8, 5),
    LEATHER(ProficiencyType.LIGHT, ArmorType.NO_MAX_DEX_BONUS, 1, false, 1, 10, 10),
    STUDDED_LEATHER(ProficiencyType.LIGHT, ArmorType.NO_MAX_DEX_BONUS, 2, false, 1, 13, 45),

    HIDE(ProficiencyType.MEDIUM, 2, 2, false, 1, 12, 10),
    CHAIN_SHIRT(ProficiencyType.MEDIUM, 2, 3, false, 1, 20, 50),
    SCALE_MAIL(ProficiencyType.MEDIUM, 2, 4, true, 1, 45, 50),
    BREAST_PLATE(ProficiencyType.MEDIUM, 2, 4, false, 1, 20, 400),
    HALF_PLATE(ProficiencyType.MEDIUM, 2, 5, true, 1, 40, 750),

    RING_MAIL(ProficiencyType.HEAVY, 0, 4, true, 1, 40, 30),
    CHAIN_MAIL(ProficiencyType.HEAVY, 0, 6, true, 13, 55, 75),
    SPLINT(ProficiencyType.HEAVY, 0, 7, true, 15, 60, 200),
    PLATE(ProficiencyType.HEAVY, 0, 8, true, 15, 65, 1500),

    SHIELD(ProficiencyType.SHIELD, 0, 2, false, 1, 6, 10);

    public static final int NO_MAX_DEX_BONUS = -1;

    public enum ProficiencyType implements I18nEnum {

        LIGHT, MEDIUM, HEAVY, SHIELD;

        @Override
        public String getName() {
            return Labels.getInstance().getMessage(getKey(name(), "name"));
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    private ProficiencyType proficiencyType;
    private int             maxDexBonus;
    private int             baseArmorClass;
    private boolean         stealthDisavantage;
    private int             minStrength;       // The minimum strength to be able to wear the armor.
    private Double          baseWeight;
    private int             basePrice;

    private ArmorType(ProficiencyType proficiencyType, int maxDexBonus, int baseArmorClass, boolean stealthDisavantage,
            int minStrength, double baseWeight, int speed) {

        this.proficiencyType = proficiencyType;
        this.maxDexBonus = maxDexBonus;
        this.baseArmorClass = baseArmorClass;
        this.stealthDisavantage = stealthDisavantage;
        this.minStrength = minStrength;
        this.baseWeight = baseWeight;
    }

    @Override
    public String getName() {
        return Labels.getInstance().getMessage(getKey(name(), "name"));
    }

    public String getDescription() {
        return Labels.getInstance().getMessage(getKey(name(), "description"));
    }

    public ProficiencyType getProficiencyType() {
        return proficiencyType;
    }

    public int getMaxDexBonus() {
        return maxDexBonus;
    }

    public int getBaseArmorClass() {
        return baseArmorClass;
    }

    public Double getBaseWeight() {
        return baseWeight;
    }

    public boolean getStealthDisavantage() {
        return stealthDisavantage;
    }

    public int getMinStrength() {
        return minStrength;
    }

    public int getBasePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return getName();
    }
}
