package com.dungeonstory.backend.data.util;

import java.util.Optional;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.ClassSpellSlots;
import com.dungeonstory.backend.data.DSClass;

public class ClassUtil {

    private ClassUtil() {
        // TODO Auto-generated constructor stub
    }

    public static Optional<CharacterClass> getCharacterClass(Character character, DSClass dsClass) {
        Optional<CharacterClass> assignedClass = character.getClasses().stream()
                .filter(characterClass -> characterClass.getClasse().equals(dsClass)).findFirst();

        return assignedClass;
    }

    public static Optional<ClassLevelBonus> getClassLevelBonus(DSClass dsClass, int level) {
        Optional<ClassLevelBonus> classLevelBonus = dsClass.getLevelBonuses().stream()
                .filter(bonus -> bonus.getLevel().getId().intValue() == level).findFirst();
        return classLevelBonus;
    }

    public static Optional<ClassSpellSlots> getClassSpellSlots(DSClass dsClass, int level) {
        Optional<ClassSpellSlots> classSpellSlots = dsClass.getSpellSlots().stream()
                .filter(slot -> slot.getLevel().getId().intValue() == level).findFirst();
        return classSpellSlots;
    }

}
