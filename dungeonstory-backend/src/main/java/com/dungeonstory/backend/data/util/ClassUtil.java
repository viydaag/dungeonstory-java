package com.dungeonstory.backend.data.util;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.ClassLevelFeature;
import com.dungeonstory.backend.data.ClassSpellSlots;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Feat;

public class ClassUtil {

    private ClassUtil() {

    }

    public static CharacterClass getCharacterClass(Character character, DSClass dsClass) {
        //        List<CharacterClass> characterClasses = character.getClasses();
        //        CharacterClass assignedClass = characterClasses.stream().filter(characterClass -> characterClass.getClasse().equals(dsClass)).findFirst()
        //                .orElse(null);

        for (CharacterClass characterClass : character.getClasses()) {
            if (characterClass.getClasse().equals(dsClass)) {
                return characterClass;
            }
        }

        return null;
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

    public static List<Feat> getClassFeaturesForLevel(DSClass dsClass, int level) {
        List<Feat> classFeatures = dsClass.getClassFeatures().stream()
                .filter(feature -> feature.getLevel().getId().intValue() == level).map(ClassLevelFeature::getFeat)
                .collect(Collectors.toList());
        return classFeatures;
    }

}
