package com.dungeonstory.ui.view.character.wizard;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.vaadin.teemu.wizards.WizardStep;
import org.vaadin.teemu.wizards.event.WizardCompletedEvent;
import org.vaadin.teemu.wizards.event.WizardStepActivationEvent;
import org.vaadin.teemu.wizards.event.WizardStepSetChangedEvent;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterClass;
import com.dungeonstory.backend.data.ClassFeature;
import com.dungeonstory.backend.data.ClassLevelBonus;
import com.dungeonstory.backend.data.DSClass;
import com.dungeonstory.backend.data.DSClass.SpellCastingType;
import com.dungeonstory.backend.data.User;
import com.dungeonstory.backend.data.util.ClassUtil;
import com.dungeonstory.backend.service.Services;
import com.dungeonstory.ui.authentication.CurrentUser;
import com.dungeonstory.ui.event.CharacterUpdatedEvent;
import com.dungeonstory.ui.event.EventBus;

public class LevelUpCharacterWizard extends CharacterWizard {

    private static final long serialVersionUID = -1566093699384428874L;

    private boolean isWorking = false;
    private String  stepActivatedAfterClass = null;

    public LevelUpCharacterWizard(Character character) {
        super();
        setOriginal(character);

        addStep(new ClassStep(this), CLASS);
        addStep(new SummaryStep(this), SUMMARY);

        getNextButton().setEnabled(false); //set the next button disabled until a value is selected
        getNextButton().setId("nextButton");
        getBackButton().setId("backButton");
        getCancelButton().setId("cancelButton");
        getFinishButton().setId("finishButton");
    }

    @Override
    public void setChosenClass(DSClass chosenClass) {
        super.setChosenClass(chosenClass);
    }

    private void resetSteps() {

        if (!isWorking) {

            isWorking = true;
            stepActivatedAfterClass = null;

            removeStepsAfterClass();

            // activates a dummy step to call the "onAdvance" method for class step
            if (idMap.get(DUMMY) == null) {
                addStep(new DummyStep(this), DUMMY);
            }
            activateStep(DUMMY, isActive(DUMMY));

            DSClass chosenClass = getChosenClass();

            CharacterClass assignedClass = ClassUtil.getCharacterClass(character, chosenClass);
            int levelUp = assignedClass.getClassLevel();

            Optional<ClassLevelBonus> classLevelBonusOpt = ClassUtil.getClassLevelBonus(chosenClass, levelUp);
            if (classLevelBonusOpt.isPresent()) {
                ClassLevelBonus classLevelBonus = classLevelBonusOpt.get();

                //check if a class specialization is available
                if (classLevelBonus.getChooseClassSpecialization()) {
                    addStep(new ClassSpecializationStep(this), CLASS_SPEC);
                    setStepActivatedAfterClass(CLASS_SPEC);
                }

                //check if there is an ability score improvement
                if (classLevelBonus.getHasAbilityScoreImprovement()) {
                    addStep(new AbilityScoreStep(this, true), ABILITY);
                    setStepActivatedAfterClass(ABILITY);
                }
            }

            //check if some class features need a choice
            List<ClassFeature> parentClassFeatures = ClassUtil.getClassFeaturesForLevel(chosenClass, levelUp)
                                                              .filter(cf -> !cf.getChildren().isEmpty())
                                                              .collect(Collectors.toList());
            if (!parentClassFeatures.isEmpty()) {
                addStep(new ClassFeatureStep(this), CLASS_FEATURE);
                setStepActivatedAfterClass(CLASS_FEATURE);
            }

            //check spell change for known spells
            if (chosenClass.getIsSpellCasting() && chosenClass.getSpellCastingType() == SpellCastingType.KNOWN) {
                addStep(new SpellStep(this), SPELL);
                setStepActivatedAfterClass(SPELL);
            }

            //summary
            addStep(new SummaryStep(this), SUMMARY);
            setStepActivatedAfterClass(SUMMARY);

            activateStep(stepActivatedAfterClass, false);

            removeStep(DUMMY, true);

            isWorking = false;
        }
    }

    private void removeStepsAfterClass() {
        removeStep(CLASS_SPEC, true);
        removeStep(CLASS_FEATURE, true);
        removeStep(ABILITY, true);
        removeStep(SPELL, true);
        removeStep(SUMMARY, true);
    }

    @Override
    public void wizardCompleted(WizardCompletedEvent event) {
        User user = Services.getUserService().read(CurrentUser.get().getId());
        user.setCharacter(character);
        character.setUser(CurrentUser.get());
        Services.getCharacterService().levelUp(character);
        EventBus.post(new CharacterUpdatedEvent());
        super.wizardCompleted(event);
    }

    private boolean activateStep(String stepId, boolean stepActivated) {
        if (!stepActivated) {
            activateStep(idMap.get(stepId));
            return true;
        }
        return stepActivated;
    }

    @Override
    public void next() {
        if (idMap.get(CLASS).equals(currentStep)) {
            //steps need to be defined following the class choice
            resetSteps();
        } else {
            super.next();
        }
    }

    public void removeStep(String id, boolean force) {
        if (force) {
            if (idMap.containsKey(id)) {
                WizardStep stepToRemove = idMap.get(id);

                if (isActive(stepToRemove)) {
                    throw new IllegalStateException("Currently active step cannot be removed.");
                }

                int indexOfLastCompletedStep = steps.indexOf(lastCompletedStep);
                if (steps.indexOf(stepToRemove) == indexOfLastCompletedStep) {
                    lastCompletedStep = steps.get(indexOfLastCompletedStep - 1);
                }

                idMap.remove(id);
                steps.remove(stepToRemove);

                // notify listeners
                fireEvent(new WizardStepSetChangedEvent(this));

                // if current step changes index, must fire a event to refresh the progress bar
                if (isCompleted(stepToRemove)) {
                    fireEvent(new WizardStepActivationEvent(this, currentStep));
                }

            }
        } else {
            super.removeStep(id);
        }
    }

    @Override
    public void activeStepChanged(WizardStepActivationEvent event) {
        super.activeStepChanged(event);
        System.out.println("Active step = " + event.getActivatedStep().getCaption());
    }

    private void setStepActivatedAfterClass(String stepName) {
        if (stepActivatedAfterClass == null) {
            stepActivatedAfterClass = stepName;
        }
    }

}
