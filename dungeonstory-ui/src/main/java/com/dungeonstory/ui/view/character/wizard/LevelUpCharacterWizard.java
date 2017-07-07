package com.dungeonstory.ui.view.character.wizard;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.vaadin.teemu.wizards.event.WizardCompletedEvent;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.DSClass.SpellCastingType;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.ui.authentication.CurrentUser;

public class LevelUpCharacterWizard extends CharacterWizard {

    private static final long serialVersionUID = -1566093699384428874L;

    public LevelUpCharacterWizard(Character character) {
        super();
        setOriginal(character);

        addStep(new ClassStep(this), CLASS);

        getNextButton().setEnabled(false); //set the next button disabled until a value is selected
    }

    @Override
    public void setChosenClass(DSClass chosenClass) {
        super.setChosenClass(chosenClass);

        removeStepsAfterClass();

        CharacterClass assignedClass = ClassUtil.getCharacterClass(character, chosenClass);
        int levelUp = assignedClass.getClassLevel();

        Optional<ClassLevelBonus> classLevelBonusOpt = ClassUtil.getClassLevelBonus(chosenClass, levelUp);
        if (classLevelBonusOpt.isPresent()) {
            ClassLevelBonus classLevelBonus = classLevelBonusOpt.get();

            //check if a class specialization is available
            if (classLevelBonus.getChooseClassSpecialization()) {
                addStep(new ClassSpecializationStep(this), CLASS_SPEC);
            }

            //check if there is an ability score improvement
            if (classLevelBonus.getHasAbilityScoreImprovement()) {
                addStep(new AbilityScoreStep(this, true), ABILITY);
            }
        }

        //check if some class features need a choice
        List<ClassFeature> parentClassFeatures = ClassUtil.getClassFeaturesForLevel(chosenClass, levelUp).filter(cf -> !cf.getChildren().isEmpty())
                .collect(Collectors.toList());
        if (!parentClassFeatures.isEmpty()) {
            addStep(new ClassFeatureStep(this), CLASS_FEATURE);
        }

        if (chosenClass.getIsSpellCasting() && chosenClass.getSpellCastingType() == SpellCastingType.KNOWN) {
            addStep(new SpellStep(this), SPELL);
        }

        addStep(new SummaryStep(this), SUMMARY);
    }

    private void removeStepsAfterClass() {
        removeStep(ABILITY);
        removeStep(SPELL);
        removeStep(SUMMARY);
    }

    @Override
    public void wizardCompleted(WizardCompletedEvent event) {
        character.setUser(CurrentUser.get());
        characterService.saveOrUpdate(character);
        super.wizardCompleted(event);
    }

}
