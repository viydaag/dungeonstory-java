package com.dungeonstory.backend.data.util;

import com.dungeonstory.backend.data.Weapon;
import com.dungeonstory.backend.data.WeaponType.HandleType;
import com.dungeonstory.backend.data.WeaponType.SizeType;

public final class EquipmentUtil {

    private EquipmentUtil() {

    }

    public static boolean isMonkWeapon(Weapon weapon) {
        if (weapon == null) {
            return true; //hands
        } else {
            return weapon.getWeaponType().getSizeType() != SizeType.HEAVY && weapon.getWeaponType().getHandleType() != HandleType.TWO_HANDED;
        }
    }

}
