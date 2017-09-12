package com.dungeonstory.backend.data.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.ClassLevelFeature;
import com.dungeonstory.backend.data.ClassSpecLevelFeature;
import com.dungeonstory.backend.data.ClassSpecialization;
import com.dungeonstory.backend.data.ClassSpellSlots;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.Level;
import com.dungeonstory.backend.service.Services;

public class ClassUtil {

    private ClassUtil() {

    }

    public static CharacterClass getCharacterClass(Character character, DSClass dsClass) {
        Set<CharacterClass> characterClasses = character.getClasses();
        CharacterClass assignedClass = characterClasses.stream()
                                                       .filter(characterClass -> characterClass.getClasse().equals(dsClass))
                                                       .findFirst()
                                                       .orElse(null);
        return assignedClass;
    }

    public static Optional<ClassLevelBonus> getClassLevelBonus(DSClass dsClass, int level) {
        if (dsClass == null) {
            return Optional.empty();
        }
        Optional<ClassLevelBonus> classLevelBonus = dsClass.getLevelBonuses()
                                                           .stream()
                                                           .filter(bonus -> bonus.getLevel().getId().intValue() == level)
                                                           .findFirst();
        return classLevelBonus;
    }

    public static Optional<ClassSpellSlots> getClassSpellSlots(DSClass dsClass, int level) {
        Optional<ClassSpellSlots> classSpellSlots = dsClass.getSpellSlots()
                                                           .stream()
                                                           .filter(slot -> slot.getLevel().getId().intValue() == level)
                                                           .findFirst();
        return classSpellSlots;
    }

    public static Stream<ClassFeature> getClassFeaturesForLevel(DSClass dsClass, int level) {
        Stream<ClassFeature> classFeatures = dsClass.getClassFeatures()
                                                    .stream()
                                                    .filter(feature -> feature.getLevel().getId().intValue() == level)
                                                    .map(ClassLevelFeature::getFeature);
        return classFeatures;
    }

    public static List<ClassFeature> getClassFeaturesForLevel(ClassSpecialization classSpec, int level) {
        List<ClassFeature> classFeatures = classSpec.getClassSpecFeatures()
                                                    .stream()
                                                    .filter(feature -> feature.getLevel().getId().intValue() == level)
                                                    .map(ClassSpecLevelFeature::getFeature)
                                                    .collect(Collectors.toList());
        return classFeatures;
    }

    /**
     * Get all class features for a character.
     * @param character
     * @return
     */
    public static Set<ClassFeature> getAllCharacterClassFeatures(Character character) {
        Set<ClassFeature> allClassFeatures = new HashSet<>();
        character.getClasses().forEach(cc -> allClassFeatures.addAll(cc.getClassFeatures()));
        return allClassFeatures;
    }

    /**
     * Get all class features that have no parent for a character.
     * @param character
     * @return
     */
    public static List<ClassFeature> getAllRootCharacterClassFeatures(Character character) {
        List<ClassFeature> allClassFeatures = new ArrayList<ClassFeature>();
        character.getClasses().forEach(
                cc -> allClassFeatures.addAll(cc.getClassFeatures().stream().filter(cf -> cf.getParent() == null).collect(Collectors.toList())));
        return allClassFeatures;
    }

    /**
     * Gets all assigned children class features for a particular parent class feature.
     * @param character
     * @param parent
     * @return
     */
    public static List<ClassFeature> getChildrenCharacterClassFeatures(Character character, ClassFeature parent) {
        Set<ClassFeature> allClassFeatures = getAllCharacterClassFeatures(character);
        List<ClassFeature> childrenClassFeatures = new ArrayList<>();
        boolean isFound = allClassFeatures.stream().filter(feature -> feature.equals(parent)).findFirst().isPresent();
        if (isFound) {
            childrenClassFeatures.addAll(allClassFeatures);
            childrenClassFeatures.retainAll(parent.getChildren());
        }

        return childrenClassFeatures;
    }

    /**
     * Gets the class level feature for a character class and a feature.
     * @param characterClass
     * @param feature
     * @return
     */
    public static ClassLevelFeature getClassLevelFeature(CharacterClass characterClass, ClassFeature feature) {
        Level level = Services.getLevelService().read(new Long(characterClass.getClassLevel()));
        ClassLevelFeature classLevelFeature = characterClass.getClasse()
                                                            .getClassFeatures()
                                                            .stream()
                                                            .filter(classFeature -> classFeature.getLevel().equals(level)
                                                                    && classFeature.getFeature().equals(feature))
                                                            .findFirst()
                                                            .orElse(null);
        return classLevelFeature;
    }

}
