package com.dungeonstory.ui.view.character.wizard;

import java.io.Serializable;

import org.vaadin.teemu.wizards.WizardStep;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.ui.view.character.wizard.form.CharacterWizardStepForm;

public abstract class CharacterWizardStep<T> implements WizardStep, Serializable {

    private static final long serialVersionUID = 1566483351479714073L;

    protected CharacterWizard            wizard;
    protected CharacterWizardStepForm<T> form;
    protected Character                  stepCharacter;           //character state at the start of the step

    public CharacterWizardStep(CharacterWizard wizard) {
        this.wizard = wizard;
    }

    public CharacterWizard getWizard() {
        return wizard;
    }

    @Override
    public boolean onAdvance() {
        if (form != null) {
            form.save();
        }
        wizard.setCharacterFromPreviousStep(stepCharacter);
        return true;
    }

    @Override
    public boolean onBack() {
        wizard.setCharacter(wizard.getCharacterFromPreviousStep());
        return true;
    }

    public void afterActivateStep() {
        if (form != null) {
            setSaveButton();
            form.adjustButtons();
        }

        //sets the character from previous step
        int index = wizard.getSteps().indexOf(this);
        if (index > 0) {
            CharacterWizardStep<?> previousStep = (CharacterWizardStep<?>) wizard.getSteps().get(index - 1);
            wizard.setCharacterFromPreviousStep(previousStep.getStepCharacter());
        }

        stepCharacter = wizard.getCharacter().clone();
    }

    protected void setSaveButton() {
        if (form != null) {
            form.setSaveButton(wizard.getNextButton());
        }
    }

    public Character getStepCharacter() {
        return stepCharacter;
    }

}
