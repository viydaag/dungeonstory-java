package com.dungeonstory.view.character;

import java.util.Optional;

import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.util.ClassUtil;

public class NewCharacterWizard extends CharacterWizard {

    private static final long serialVersionUID = -8481346074235692258L;

    public NewCharacterWizard() {
        super();

        addStep(new RaceStep(this), RACE);
        addStep(new ClassStep(this), CLASS);
        addStep(new AbilityScoreStep(this), ABILITY);

        getNextButton().setEnabled(false); //set the next button disabled until a value is selected
    }

    @Override
    public void setChosenClass(DSClass chosenClass) {
        super.setChosenClass(chosenClass);

        removeAllOptinalSteps();

        if (chosenClass.getIsSpellCasting()) {
            addStep(new SpellStep(this), SPELL);
        }

        Optional<ClassLevelBonus> classLevelBonusOpt = ClassUtil.getClassLevelBonus(chosenClass, 1);
        if (classLevelBonusOpt.isPresent()) {
            ClassLevelBonus classLevelBonus = classLevelBonusOpt.get();
            if (classLevelBonus.getFavoredEnemy()) {
                addStep(new HunterStep(this), HUNTER);
            }
        }

        addStep(new SummaryStep(this), SUMMARY);
    }

    private void removeAllOptinalSteps() {
        removeStep(SPELL);
        removeStep(HUNTER);
        removeStep(SUMMARY);
    }

}
