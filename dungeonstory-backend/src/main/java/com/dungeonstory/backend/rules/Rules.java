package com.dungeonstory.backend.rules;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.data.enums.Ability;
import com.dungeonstory.backend.data.enums.Feat;
import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.Monster;
import com.dungeonstory.backend.data.util.ModifierUtil;
import com.dungeonstory.backend.service.Services;

public final class Rules {
    
    public static List<SavingThrow> getMonsterSavingThrows(Monster monster) {
        List<SavingThrow> savingThrows = new ArrayList<>();
        for (Ability ability : monster.getSavingThrowProficiencies()) {
            int modifier = ModifierUtil.getSavingThrowModifier(monster, ability);
            savingThrows.add(new SavingThrow(ability, modifier));
        }
        return savingThrows;
    }
    
    public static int calculateCharacterLifePoints(Character character, int level) {
        int nbLifePoints = 0;
        for (CharacterClass cc : character.getClasses()) {
            nbLifePoints += (cc.getClassLevel() * (cc.getClasse().getLifePointPerLevel()
                    + ModifierUtil.getAbilityModifier(character.getConstitution())));
        }
        if (Services.getCharacterService().hasFeat(character, Feat.TOUGH)) {
            nbLifePoints += (2 * level);
        }
        return nbLifePoints;
    }

}
