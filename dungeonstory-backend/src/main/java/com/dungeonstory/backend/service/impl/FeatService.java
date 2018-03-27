package com.dungeonstory.backend.service.impl;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.enums.Feat;
import com.dungeonstory.backend.data.util.ModifierUtil;
import com.dungeonstory.backend.service.FeatDataService;

public class FeatService implements FeatDataService {

    private static FeatService instance = null;
    public static synchronized FeatService getInstance() {
        if (instance == null) {
            instance = new FeatService();
        }
        return instance;
    }

    @Override
    public Set<Feat> findAllFeatsExcept(Feat feat) {
        return EnumSet.complementOf(EnumSet.of(feat));
    }

    @Override
    public Set<Feat> findAllUnassignedFeats(Character character) {
        Set<Feat> feats = EnumSet.complementOf(EnumSet.copyOf(character.getFeats()));
        return feats.stream().filter(feat -> isFeatAvailable(feat, character)).collect(Collectors.toSet());
    }

    /**
     * Check if a character has the right to assign a feat to itself
     * according to feat prerequisite.
     * @param feat
     * @param character
     * @return
     */
    private boolean isFeatAvailable(Feat feat, Character character) {
        boolean isAvailable = true;
        switch (feat.getPrerequisiteType()) {
            case ABILITY:
                int score = ModifierUtil.getAbilityScore(character, feat.getPrerequisiteAbility());
                if (score < feat.getPrerequisiteAbilityScore()) {
                    isAvailable = false;
                }
                break;

            case ARMOR_PROFICIENCY:
                if (!character.getArmorProficiencies().contains(feat.getPrerequisiteArmorProficiency())) {
                    isAvailable = false;
                }
                break;

            case CAST_SPELL:
                isAvailable = character.getClasses().stream().map(CharacterClass::getClasse).anyMatch(
                        classe -> classe.getIsSpellCasting());
                break;

            case NONE:
            default:
                break;
        }
        return isAvailable;
    }


}
