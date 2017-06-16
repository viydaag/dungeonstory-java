package com.dungeonstory.backend.data.util;

import com.dungeonstory.backend.data.Ability;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.HasStats;
import com.dungeonstory.backend.data.Monster;

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
    
    public static int getSavingThrowModifier(Character character, Ability ability) {
        int savingThrowModifier = 0;
        if (character != null && ability != null) {
           boolean isProficient = character.getSavingThrowProficiencies().contains(ability);
           int abilityScore = getAbilityScore(character, ability);
           savingThrowModifier = getAbilityModifier(abilityScore);
           if (isProficient) {
               savingThrowModifier += character.getLevel().getProficiencyBonus();
           }
        }
        return savingThrowModifier;
    }
    
    public static int getSavingThrowModifier(Monster monster, Ability ability) {
        int savingThrowModifier = 0;
        if (monster != null && ability != null) {
           boolean isProficient = monster.getSavingThrowProficiencies().contains(ability);
           if (isProficient) {
               int abilityScore = getAbilityScore(monster, ability);
               savingThrowModifier = getAbilityModifier(abilityScore);
               savingThrowModifier += monster.getChallengeRating().getProficiencyBonus();
           }
        }
        return savingThrowModifier;
    }
    
    public static int getAbilityScore(HasStats stats, Ability ability) {
        int score = 0;
        if (ability.getAbbreviation().equals("For")) {
            score = stats.getStrength();
        } else if (ability.getAbbreviation().equals("Dex")) {
            score = stats.getDexterity();
        } else if (ability.getAbbreviation().equals("Con")) {
            score = stats.getConstitution();
        } else if (ability.getAbbreviation().equals("Int")) {
            score = stats.getIntelligence();
        } else if (ability.getAbbreviation().equals("Sag")) {
            score = stats.getWisdom();
        } else if (ability.getAbbreviation().equals("Cha")) {
            score = stats.getCharisma();
        }
        return score;
    }

}
