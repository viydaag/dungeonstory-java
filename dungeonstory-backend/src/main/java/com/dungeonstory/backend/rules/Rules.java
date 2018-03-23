package com.dungeonstory.backend.rules;

import java.util.ArrayList;
import java.util.List;

import com.dungeonstory.backend.data.enums.Ability;
import com.dungeonstory.backend.data.Monster;
import com.dungeonstory.backend.data.util.ModifierUtil;

public final class Rules {
    
    public static List<SavingThrow> getMonsterSavingThrows(Monster monster) {
        List<SavingThrow> savingThrows = new ArrayList<>();
        for (Ability ability : monster.getSavingThrowProficiencies()) {
            int modifier = ModifierUtil.getSavingThrowModifier(monster, ability);
            savingThrows.add(new SavingThrow(ability, modifier));
        }
        return savingThrows;
    }

}
