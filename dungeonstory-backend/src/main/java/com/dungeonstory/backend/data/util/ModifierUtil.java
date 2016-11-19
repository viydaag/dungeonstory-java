package com.dungeonstory.backend.data.util;

public final class ModifierUtil {

    public static int getAbilityModifier(int value) {
        return Math.floorDiv(value, 2) - 5;
    }

    public static int getPositiveAbilityModifier(int value) {
        if (value < 10) {
            return 0;
        }
        return getAbilityModifier(value);
    }

}
